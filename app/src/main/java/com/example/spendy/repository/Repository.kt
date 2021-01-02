package com.example.spendy.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.spendy.*
import com.example.spendy.adapters.ExpenseIncomeAdapter
import com.example.spendy.models.Budget
import com.example.spendy.models.SignInModel
import com.example.spendy.models.User
import com.example.spendy.ui.homepage.HomepageActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

private lateinit var mauth: FirebaseAuth
// ...
// Initialize Firebase Auth


private val auth = FirebaseAuth.getInstance()
private val db = Firebase.firestore

private lateinit var mutableBudgetList: MutableList<Budget>


class Repository {


    //Sign Up
    fun signUp(user: User): Boolean {

        var res = true

        auth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener { task ->

            if (task.isSuccessful) {


                println("success")

                addUser(user)

            } else {
                println(task.exception.toString())
                res = false
            }
        }


        return res
    }

    //Log In
    fun logIn(signInModel: SignInModel,context:Context): String {
        mauth = Firebase.auth
        var res="false"

        /*auth.signInWithEmailAndPassword(signInModel.email, signInModel.password).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                println("success")
                res=true

            }else{
                println(task.exception.toString())
                res = false
            }
        }*/
        mauth.signInWithEmailAndPassword(signInModel.email, signInModel.password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    res="true"
                    println(mauth.currentUser?.email+" repo"+res)
                    Toast.makeText(context,"Sing In succes fasdf",Toast.LENGTH_SHORT).show()

                }
        }

        println(mauth.currentUser?.email+" repo"+res)
        return res
    }
    /*
    mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }

                // ...
            }
        });
     */

    //Add User
    private fun addUser(user: User) {

        val userMap = hashMapOf(
                "name" to user.name,
                "surname" to user.surname,
                "email" to user.email.toString(),
                "pasword" to user.password
        )

        db.collection("Users").document(user.email).set(userMap)

    }

    //Add Income
    fun addIncome(budget: Budget) {

        val budgetMap = hashMapOf(

                "type" to budget.type,
                "amount" to budget.amount,
                "category" to budget.category,
                "time" to budget.time

        )

        val uuid = UUID.randomUUID().toString()

        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget").document(uuid).set(budgetMap)

    }


    //Add Expense
    fun addExpense(budget: Budget) {

        val budgetMap = hashMapOf(

                "type" to budget.type,
                "amount" to budget.amount,
                "category" to budget.category,
                "time" to budget.time

        )

        val uuid = UUID.randomUUID().toString()

        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget").document(uuid).set(budgetMap)

    }

    //Get Budget

    fun getBudget(): MutableList<Budget> {

        mutableBudgetList = mutableListOf()//?



        /*

        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget").addSnapshotListener { snapshot, e ->


            if (e != null || snapshot == null) {

                return@addSnapshotListener
            }


           // budgetList = snapshot.toObjects(Budget::class.java)



        }
            */




        return mutableBudgetList
    }

}