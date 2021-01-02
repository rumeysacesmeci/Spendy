package com.example.spendy.ui.signUp

import android.os.Bundle
import android.util.Patterns
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

        val email = txtEmail.text.toString()
        val password = txtPassword.text.toString()

        var verifySignUp = true


        if (txtEmail.text.toString().isEmpty()){

            txtEmail.error = "Please Enter Your E-Mail"
            txtEmail.requestFocus()

            verifySignUp = false
        }

        if (txtName.text.toString().isEmpty()){

            txtName.error = "Please Enter Your Name"
            txtName.requestFocus()

            verifySignUp = false
        }

        if (txtSurname.text.toString().isEmpty()){

            txtSurname.error = "Please Enter Your Surname"
            txtSurname.requestFocus()

            verifySignUp = false
        }

        if (txtPassword.text.toString().isEmpty()){

            txtPassword.error = "Please Enter Your Password"
            txtPassword.requestFocus()

            verifySignUp = false
        }

        if (txtPasswordConfirm.text.toString().isEmpty()){

            txtPasswordConfirm.error = "Please Confirm Your Password"
            txtPasswordConfirm.requestFocus()

            verifySignUp = false
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(txtEmail.text.toString()).matches()){

            txtEmail.error = "Please Enter a Valid Email"
            txtEmail.requestFocus()

            verifySignUp = false

        }

        //Confirm Password
        if (!txtPassword.text.toString().equals(txtPasswordConfirm.text.toString())){

            Toast.makeText(baseContext, "Passwords did not Match", Toast.LENGTH_LONG).show()
            txtPasswordConfirm.requestFocus()

            verifySignUp = false
        }

        //Verify Sign Up
        if(!verifySignUp){

            return
        }

        val result = repo.signUp(getSignUpValues())


        if(!result){
            Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
            return
        }
             Toast.makeText(baseContext, "Success.", Toast.LENGTH_SHORT).show()

        finish()

    }



    //Get Sign Up Value
   private fun getSignUpValues():User{

        val name = txtName.text.toString();
        val surname = txtSurname.text.toString();
        val email = txtEmail.text.toString();
        val password = txtPassword.text.toString();


        return User(name, surname, email, password)
    }

    private fun isEmpty():Boolean{

        val res = true

        val isEmail = txtEmail.text.toString().isEmpty()
        val isName = txtName.text.toString().isEmpty()
        val isSurname = txtSurname.text.toString().isEmpty()
        val isPassword = txtPassword.text.toString().isEmpty()
        val isConfirmed = txtPasswordConfirm.text.toString().isEmpty()



        return isEmail && isName && isSurname && isPassword && isConfirmed
    }

    private fun verifySignUp(){


    }


}