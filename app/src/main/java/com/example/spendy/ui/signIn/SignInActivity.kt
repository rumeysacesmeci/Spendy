package com.example.spendy.ui.signIn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spendy.ui.homepage.HomepageActivity
import com.example.spendy.models.SignInModel
import com.example.spendy.R
import com.example.spendy.repository.Repository
import com.example.spendy.ui.signUp.SignUpActivity
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    private val repository = Repository()
    private val auth = FirebaseAuth.getInstance()

    //private val sign = SignInModel(txtEmail.toString(),txtPassword.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

       //Firebase.auth.signOut()

       if (auth.currentUser!=null){

            val nvgToHomePage = Intent(this@SignInActivity, HomepageActivity::class.java)
            startActivity(nvgToHomePage)

            finish()

        }

      /*  btnSignIn.setOnClickListener {
            val credential = EmailAuthProvider.getCredential(txtEmail.toString(), txtPassword.toString())
        }
*/



    }


    // Get Sign In Values
    private fun getSignInValues(): SignInModel {

        val email = txtEmail.text.toString()
        val password = txtPassword.text.toString()

        return SignInModel(email,password)
    }


    //LogIn
    fun logIn(view: View) {
         //val auth = FirebaseAuth.getInstance()
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
    private fun verifyLogin(): Boolean {

        var res = true

        if (txtEmail.text.toString().isEmpty() && txtPassword.text.toString().isEmpty()) {

            res = false

        }

        return res
    }

    //Navigate To SignUp page
    fun signUp(view: View){

        val nvgToSignUp = Intent(this@SignInActivity, SignUpActivity::class.java)
        startActivity(nvgToSignUp)
    }

}