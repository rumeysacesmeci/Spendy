package com.example.spendy.ui.signIn


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.spendy.R
import com.example.spendy.models.SignInModel
import com.example.spendy.repository.Repository
import com.example.spendy.ui.homepage.HomepageActivity
import com.example.spendy.ui.signUp.SignUpActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.txtEmail
import kotlinx.android.synthetic.main.activity_sign_in.txtPassword


class SignInActivity : AppCompatActivity() {

    companion object {
        private const val RC_SIGN_IN = 120
    }

    private lateinit var googleSignInClient: GoogleSignInClient
    private val auth = FirebaseAuth.getInstance()
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)



        mAuth = Firebase.auth


        //Checking Current User
        if (auth.currentUser != null) {

            val nvgToHomePage = Intent(this@SignInActivity, HomepageActivity::class.java)
            startActivity(nvgToHomePage)

            finish()

        }

        //Checking Token
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //Btn google Listener
        btnGoogle.setOnClickListener {
            logInGoogle()
        }


    }

    // Get Sign In Values
    private fun getSignInValues(): SignInModel {

        val email = txtEmail.text.toString()
        val password = txtPassword.text.toString()
        return SignInModel(email, password)

    }

    //LogIn with GOOGLE
    fun logInGoogle() {

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            //val exception=task.exception

            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately

                Log.w("SignInActivity", "Google sign in failed", e)
                // ...
            }


        }
    }


    //LogIn
    fun logIn(view: View) {

        if (!verifyLogin()) {

            Toast.makeText(baseContext, "Please Check Your Email or Password.", Toast.LENGTH_SHORT)
                .show()
            return
        }



        auth.signInWithEmailAndPassword(txtEmail.text.toString(), txtPassword.text.toString())
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    Toast.makeText(baseContext, "Login Success.", Toast.LENGTH_SHORT).show()
                    val nvgToHomepage = Intent(this@SignInActivity, HomepageActivity::class.java)
                    startActivity(nvgToHomepage)

                } else {

                    Toast.makeText(baseContext, "Login Failed.", Toast.LENGTH_SHORT).show()
                }

            }

    }

    //Verify Login

    private fun verifyLogin(): Boolean {

        var res = true

        if (txtEmail.text.toString().isEmpty() || txtPassword.text.toString().isEmpty()) {

            res = false

        }
        if (txtPassword.text.toString().length < 6) {
            txtPassword.error = getString(R.string.password_not_valid_error)
            txtPassword.requestFocus()
            res = false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail.text.toString()).matches()) {

            txtEmail.error = getString(R.string.email_validation_error)
            txtEmail.requestFocus()

            res = false

        }


        return res
    }

    //Navigate To SignUp page
    fun signUp(view: View) {

        val nvgToSignUp = Intent(this@SignInActivity, SignUpActivity::class.java)
        startActivity(nvgToSignUp)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")

                    val nvgToHomePage = Intent(this@SignInActivity, HomepageActivity::class.java)
                    startActivity(nvgToHomePage)

                    finish()


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("DENEME", "signInWithCredential:failure", task.exception)


                }

                // ...
            }
    }

    //On Forgot Password Pressed


    fun onForgotButtonPressed(view: View) {


        val builder = AlertDialog.Builder(this)


        val view = layoutInflater.inflate(R.layout.dialog_forgot_password, null)
        val email = view.findViewById<EditText>(R.id.txtForgotPassword)


        builder.setView(view)


        builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->

            forgotPassword(email)

        })

        builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ ->

        })

        builder.show()


    }


    //Forgot Password.
    private fun forgotPassword(email: EditText) {


        if (email.text.toString().isEmpty()) {

            txtEmail.error = "Please Enter Your E-Mail"
            txtEmail.requestFocus()

            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {

            txtEmail.error = "Please Enter a Valid Email"
            txtEmail.requestFocus()

            return
        }

        auth.sendPasswordResetEmail(email.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Toast.makeText(
                        baseContext,
                        "E-Mail has been sent,Please Check Your E-Mail",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }


    }
}