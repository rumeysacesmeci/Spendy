package com.example.spendy.models

data class SignInModel(

        val email:String,
        val password:String


)

data class ExpenseIncome(
        var type:Int,
        var amount:Double,
        var categoryName:String
)


data class User(

        val name:String,
        val surname:String,
        val email:String,
        val password:String


)

data class Budget(


        val type:Int=0,
        val amount :Double=0.0,
        val category:String="",
        val time : String=""
        


)