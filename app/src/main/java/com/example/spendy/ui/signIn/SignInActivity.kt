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
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    private val repository = Repository()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)



        if (auth.currentUser!=null){

            val nvgToHomePage = Intent(this@SignInActivity, HomepageActivity::class.java)
            startActivity(nvgToHomePage)

            finish()

        }




    }


    // Get Sign In Values
    private fun getSignInValues(): SignInModel {

        val email = txtEmail.text.toString()
        val password = txtPassword.text.toString()

        return SignInModel(email, password)
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