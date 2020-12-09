package com.example.spendy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spendy.DatabaseOperations.DatabaseAcces.*
import com.example.spendy.Homepage.HomepageActivity
import com.example.spendy.Models.SignInModel
import com.example.spendy.Repository.Repository
import com.example.spendy.SignUp.SignUpActivity
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignIn : AppCompatActivity() {

    private val repository = Repository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        dbcopy()
        printUsers()


        //Intents
        val nvgToSignUp = Intent(this@SignIn,SignUpActivity::class.java )
        val nvgToHomePage = Intent(this@SignIn, HomepageActivity::class.java)


        //UserDao().insert(aa,1,"Resul","Ekinci","resulekinci10@gmail.com",123456)


        //Sign IN
        signIn(nvgToHomePage)

        //SignUp
        signUp(nvgToSignUp)




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
        val password = txtPassword.text.toString()

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