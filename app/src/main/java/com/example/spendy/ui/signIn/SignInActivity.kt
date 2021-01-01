package com.example.spendy.ui.signIn


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    companion object{
        private const val RC_SIGN_IN=120
    }

    private lateinit var googleSignInClient: GoogleSignInClient
    private val repository = Repository()
    private val auth = FirebaseAuth.getInstance()
    private lateinit var mAuth: FirebaseAuth





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mAuth = Firebase.auth

        if (auth.currentUser!=null){

            val nvgToHomePage = Intent(this@SignInActivity, HomepageActivity::class.java)
            startActivity(nvgToHomePage)

            finish()

        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient=GoogleSignIn.getClient(this,gso)

        btnGoogle.setOnClickListener{
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
    fun logInGoogle(){

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

                    Log.w("SignInActivity" , "Google sign in failed", e)
                    // ...
                }


        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    Toast.makeText(this,"Sign Up success",Toast.LENGTH_LONG).show()
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


    //LogIn
    fun logIn(view: View) {
        
       var result = repository.logIn(getSignInValues())

        if(!result){
            Toast.makeText(baseContext, "Login failed.", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(baseContext, "Success.", Toast.LENGTH_SHORT).show()

        val nvgToHomePage = Intent(this@SignInActivity, HomepageActivity::class.java)
        startActivity(nvgToHomePage)

        finish()

    }

    //Navigate To SignUp page
    fun signUp(view: View){

        val nvgToSignUp = Intent(this@SignInActivity, SignUpActivity::class.java)
        startActivity(nvgToSignUp)
    }

}