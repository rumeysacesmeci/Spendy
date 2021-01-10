package com.example.spendy.repository


import com.example.spendy.models.Budget
import com.example.spendy.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


private val auth = FirebaseAuth.getInstance()
private val db = Firebase.firestore
private var timeCount = 0
private lateinit var mutableBudgetList: MutableList<Budget>


class Repository {


    //Sign Up
    fun signUp(user: User): Boolean {

        var res = true

        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->

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

        timeCount++
        val budgetMap = hashMapOf(

            "type" to budget.type,
            "amount" to budget.amount,
            "category" to budget.category,
            "time" to timeCount.toString()

        )

        val uuid = UUID.randomUUID().toString()

        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget")
            .document(uuid).set(budgetMap)

    }


}