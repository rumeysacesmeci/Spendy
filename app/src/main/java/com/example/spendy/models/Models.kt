package com.example.spendy.models



//Model for Sign In
data class SignInModel(

        val email:String,
        val password:String


)

//Model for Categories
data class Categories(
        var categoryName:String
)

//Model for User
data class User(

        val name:String,
        val surname:String,
        val email:String,
        val password:String


)
//Model for Budget
data class Budget(


        val type:Int=0,
        val amount :Double=0.0,
        val category:String="",
        val time :String=""



)
