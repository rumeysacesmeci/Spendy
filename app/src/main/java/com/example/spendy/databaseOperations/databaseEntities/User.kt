package com.example.spendy

data class User(
    var AccountId: Int,
    var Name:String,
    var Surname:String,
    var EMail:String,
    var Password:String) {
    //deneme fdsafdda
    // fasdfadsf

    override fun toString(): String {
        return "$Name  $Surname $Password "
    }
}
