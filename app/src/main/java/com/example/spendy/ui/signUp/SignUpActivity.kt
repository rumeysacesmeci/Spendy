package com.example.spendy.ui.signUp


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spendy.R
import com.example.spendy.models.User
import com.example.spendy.repository.Repository
import com.google.android.material.textfield.TextInputEditText
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

        if(checkCredentials()) {
            val result = repo.signUp(getSignUpValues())

            if(!result){
                Toast.makeText(baseContext, "Registration failed!", Toast.LENGTH_SHORT).show()
                return
            }else{
                Toast.makeText(baseContext, "Registration successful!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }else{
            Toast.makeText(baseContext, "Registration failed!", Toast.LENGTH_SHORT).show()
            return
        }

    }

    //Control for inputs
    private fun checkCredentials(): Boolean {
        val email = txtEmail.text.toString()
        val name = txtName.text.toString()
        val surname = txtSurname.text.toString()
        val password = txtPassword.text.toString()
        val passwordConfirm = txtPasswordConfirm.text.toString()

        if(password.isEmpty() || password.length < 6){
            showError(txtPassword, "Password must be min 6 character!")
            return false
        }
        if(email.isEmpty() || !email.contains("@")){
            showError(txtEmail, "Email is not valid!")
            return false
        }
        if(passwordConfirm.isEmpty() || !passwordConfirm.equals(password)){
            showError(txtPasswordConfirm, "Password not match.")
            return false
        }
        if(name.isEmpty()){
            showError(txtName, "Invalid Name!")
            return false
        }
        if(surname.isEmpty()){
            showError(txtSurname, "Invalid Surname!")
            return false
        }
        return true

    }

    //Error message for invalid input types
    private fun showError(input: TextInputEditText?, s: String) {
        if (input != null) {
            input.setError(s)
        }
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