package com.example.spendy.ui.signIn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spendy.ForgotPasswordActivity
import com.example.spendy.R
import com.example.spendy.models.SignInModel
import com.example.spendy.repository.Repository
import com.example.spendy.ui.homepage.HomepageActivity
import com.example.spendy.ui.signUp.SignUpActivity
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    private val repository = Repository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        dbcopy()
        printUsers()


        //Intents
        val nvgToSignUp = Intent(this@SignInActivity,SignUpActivity::class.java )
        val nvgToHomePage = Intent(this@SignInActivity, HomepageActivity::class.java)
        val nvgToForgotPassword = Intent(this@SignInActivity, ForgotPasswordActivity::class.java)


        //UserDao().insert(aa,1,"Resul","Ekinci","resulekinci10@gmail.com",123456)


        //Sign IN
        signIn(nvgToHomePage)

        //SignUp
        signUp(nvgToSignUp)

        //Forgot Password
        forgotPassword(nvgToForgotPassword)



    }

    //Print Users

    private fun printUsers(){

    val list = repository.getUsers(this)

        list.forEach {

            Log.e("TAG",it.toString())
        }


    }

    //SignIn Button
    private fun signIn(intent:Intent){

        btnSignIn.setOnClickListener{

            if(verifyLogIn()) {
                Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }
        }

    }

    //SignUp Button
    private fun signUp(intent:Intent){


        tvSignUp.setOnClickListener{
            startActivity(intent)
        }

    }

    //Forgot Password
    private fun forgotPassword(intent:Intent){


        tvForgotPassword.setOnClickListener{
            startActivity(intent)
        }

    }

    // Verify Login
   private fun verifyLogIn():Boolean{

        val signInModel = getSignInValues()

        val list = repository.getUsers(this)


        list.forEach {

            if(it.EMail.equals(signInModel.email) && it.Password.equals(signInModel.password)){
                return true
            }
        }

        return false


        /*for (user in list){
            if(user.EMail.equals(signInModel.email) && user.Password.equals(signInModel.password)){

              return true
            }
        }

        return false*/
    }

    // Get Sign In Values
    private fun getSignInValues():SignInModel{

        val email = txtEmail.text.toString()
        val password = txtPassword.toString()

        return SignInModel(email,password)
    }

    //DB Copy
    private fun dbcopy(){

        val db= DatabaseCopyHelper(this)

        try{
            db.createDataBase()
        }catch(e:Exception){
            e.printStackTrace()
        }

        try{
            db.openDataBase()
        }catch(e:Exception){
            e.printStackTrace()
        }


    }
}