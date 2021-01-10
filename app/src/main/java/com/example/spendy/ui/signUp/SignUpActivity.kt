package com.example.spendy.ui.signUp


import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.spendy.R
import com.example.spendy.models.User
import com.example.spendy.repository.Repository
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {


    //Initiliaze
    private val repo = Repository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


    }

    //SignUp
    fun signUp(view: View) {

        val email = txtEmail.text.toString()
        val password = txtPassword.text.toString()

        var verifySignUp = true

        //Controlling E-Mail
        if (txtEmail.text.toString().isEmpty()) {

            txtEmail.error = getString(R.string.empty_email_error)

            txtEmail.requestFocus()

            verifySignUp = false
        }
        //Controlling Name
        if (txtName.text.toString().isEmpty()) {

            txtName.error = getString(R.string.empty_name_error)
            txtName.requestFocus()

            verifySignUp = false
        }
        //Controlling Surname
        if (txtSurname.text.toString().isEmpty()) {

            txtSurname.error = getString(R.string.empty_surname_error)
            txtSurname.requestFocus()

            verifySignUp = false
        }
        //Controlling Password
        if (txtPassword.text.toString().isEmpty() || password.length < 6) {

            txtPassword.error = getString(R.string.empty_password_error)
            txtPassword.requestFocus()

            verifySignUp = false
        }
        //Controlling Password Confirm
        if (txtPasswordConfirm.text.toString().isEmpty()) {

            txtPasswordConfirm.error = getString(R.string.empty_password_confirm_error)
            txtPasswordConfirm.requestFocus()

            verifySignUp = false
        }

        //Controlling E-Mail Pattern
        if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail.text.toString()).matches()) {

            txtEmail.error = getString(R.string.email_validation_error)
            txtEmail.requestFocus()

            verifySignUp = false

        }

        //Confirm Password
        if (!txtPassword.text.toString().equals(txtPasswordConfirm.text.toString())) {

            Toast.makeText(baseContext, getString(R.string.password_match_error), Toast.LENGTH_LONG)
                .show()
            txtPasswordConfirm.requestFocus()

            verifySignUp = false
        }

        //Verify Sign Up
        if (!verifySignUp) {

            return
        }

        if (checkCredentials()) {
            val result = repo.signUp(getSignUpValues())

            if (!result) {
                Toast.makeText(baseContext, "Registration failed!", Toast.LENGTH_SHORT).show()
                return
            } else {
                Toast.makeText(baseContext, "Registration successful!", Toast.LENGTH_SHORT).show()
                finish()
            }
        } else {
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

        if (password.isEmpty() || password.length < 6) {
            showError(txtPassword, "Password must be min 6 character!")
            return false
        }
        if (email.isEmpty() || !email.contains("@")) {
            showError(txtEmail, "Email is not valid!")
            return false
        }
        if (passwordConfirm.isEmpty() || !passwordConfirm.equals(password)) {
            showError(txtPasswordConfirm, "Password not match.")
            return false
        }
        if (name.isEmpty()) {
            showError(txtName, "Invalid Name!")
            return false
        }
        if (surname.isEmpty()) {
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
    private fun getSignUpValues(): User {

        val name = txtName.text.toString();
        val surname = txtSurname.text.toString();
        val email = txtEmail.text.toString();
        val password = txtPassword.text.toString();


        return User(name, surname, email, password)
    }


}