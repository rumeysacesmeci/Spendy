package com.example.spendy.repository

import android.content.Context
import android.widget.Toast
import com.example.spendy.*
import com.example.spendy.adapters.ExpenseIncomeAdapter
import com.example.spendy.models.Budget
import com.example.spendy.models.SignInModel
import com.example.spendy.models.User
import com.example.spendy.models.pieChartModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SnapshotMetadata
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.model.SnapshotVersion
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


private val auth = FirebaseAuth.getInstance()
private val db = Firebase.firestore
private var timeCount = 0
private lateinit var mutableBudgetList: MutableList<Budget>
private val pcList = ArrayList<pieChartModel>()

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
    fun logIn(signInModel: SignInModel): Boolean {

        var res = false


      /*  auth.signInWithEmailAndPassword(signInModel.email, signInModel.password).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                println("success")
                res = true

            } else {
                println(task.exception.toString())

            }
        }*/

        auth.signInWithEmailAndPassword(signInModel.email,signInModel.password).addOnCompleteListener { task ->

            if (task.isSuccessful){
                res=true
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

        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget").document(uuid).set(budgetMap)

    }

    fun deleteBudget(position:Int){


    }


    //Add Expense
    fun addExpense(budget: Budget) {

        timeCount++

        val budgetMap = hashMapOf(

                "type" to budget.type,
                "amount" to budget.amount,
                "category" to budget.category,
                "time" to timeCount.toString()

        )

        val uuid = UUID.randomUUID().toString()

        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget").document(uuid).set(budgetMap)

    }

    //Get Budget

    fun getBudget(): MutableList<Budget> {

        mutableBudgetList = mutableListOf()//?

        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {






                    pcList.add(pieChartModel(document.data.get("amount").toString(),document.data.get("category").toString()))


                }


                pcList.forEach {

                    println(it.amount)
                }
            }


        return mutableBudgetList
    }

}