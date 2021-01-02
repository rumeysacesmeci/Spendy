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
        mAuth=Firebase.auth


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
            println("deneme")
        }

    }

    // Get Sign In Values
    private fun getSignInValues(): SignInModel {

            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()
            return SignInModel(email, password)

    }

    //LogIn with GOOGLE
    private fun logInGoogle(){

        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
        println("deneme2")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("deneme3")
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            //val exception=task.exception
            println("deneme4")
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                    println("deneme5")
                    firebaseAuthWithGoogle(account.idToken!!)
                    println("denemeasfa")
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately

                    Log.w("SignInActivity" , "Google sign in failed", e)
                    // ...
                }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        println("deneme6"+ " "+ idToken.toString())

        mAuth.signInWithCredential(credential).addOnCompleteListener(this@SignInActivity) { task ->
            println("deneme7as")
            if(task.isSuccessful){

                println("deneme7")
                // Sign in success, update UI with the signed-in user's information
                Log.d("TAG", "signInWithCredential:success")

                val nvgToHomePage = Intent(this@SignInActivity, HomepageActivity::class.java)
                startActivity(nvgToHomePage)

                finish()
            }else{
                // If sign in fails, display a message to the user.
                Log.w("DENEME", "signInWithCredential:failure", task.exception)
                println("denemehaydaaaa")
            }

        }
       /* mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    println("deneme7")
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    Toast.makeText(this,"Sign Ip success",Toast.LENGTH_LONG).show()
                    val nvgToHomePage = Intent(this@SignInActivity, HomepageActivity::class.java)
                    startActivity(nvgToHomePage)

                    finish()


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("DENEME", "signInWithCredential:failure", task.exception)
                    println("denemehaydaaaa")


                }

                // ...
            }*/
    }


    //LogIn
    fun logIn(view: View) {
        
       /*var result = repository.logIn(getSignInValues(),baseContext)

        if(result=="false"){

            println(auth.currentUser?.email+" if içi")
            Toast.makeText(this, "Login failed fadsfasdfasdfasdf.", Toast.LENGTH_SHORT).show()
            return

        }

        val nvgToHomePage = Intent(this@SignInActivity, HomepageActivity::class.java)
        startActivity(nvgToHomePage)

        finish()
        println(auth.currentUser?.email+" en son")*/


        var signInModel=getSignInValues()
        if(signInModel.email.equals(null) && signInModel.password.equals(null)){
            return
        }else{
            auth.signInWithEmailAndPassword(signInModel.email, signInModel.password)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information

                            val user = auth.currentUser
                            println(user?.email)

                            val nvgToHomePage = Intent(this@SignInActivity, HomepageActivity::class.java)
                            startActivity(nvgToHomePage)
                            finish()

                        } else {

                            Toast.makeText(baseContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()

                        }
                    }
        }

    }

    //Navigate To SignUp page
    fun signUp(view: View){

        val nvgToSignUp = Intent(this@SignInActivity, SignUpActivity::class.java)
        startActivity(nvgToSignUp)
    }

}