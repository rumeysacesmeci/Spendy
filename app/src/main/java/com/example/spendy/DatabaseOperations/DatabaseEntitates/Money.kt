package com.example.spendy

data class Money(var MoneyId:Int,var Income:Int,var Expense :Int,var Goal:Int,
                 var AccountId: com.example.spendy.User) {
}