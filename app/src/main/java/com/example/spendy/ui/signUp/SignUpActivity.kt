package com.example.spendy.ui.signUp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spendy.R
import com.example.spendy.models.User
import com.example.spendy.repository.Repository



import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity(){



    //Initiliaze
    private val repo = Repository()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


    }

    //SignUp
    fun signUp(view : View){

        val email = txtEmail.text.toString();
        val password = txtPassword.text.toString();

        val result = repo.signUp(getSignUpValues())


        if(!result){
            Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
            return
        }
             Toast.makeText(baseContext, "Success.", Toast.LENGTH_SHORT).show()

        finish()

    }



    //Get Sign Up Value
    fun getSignUpValues():User{

        val name = txtName.text.toString();
        val surname = txtSurname.text.toString();
        val email = txtEmail.text.toString();
        val password = txtPassword.text.toString();


        return User(name, surname, email, password)
    }



}