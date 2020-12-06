package com.example.spendy.ExpenseIncome

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.spendy.R


// Adapter for card_view_expense_income.xml
class ExpenseIncomeAdapter(private val context: Context,private val expenseIncomeList:List<ExpenseIncome>)
    :RecyclerView.Adapter<ExpenseIncomeAdapter.CardViewObjectsHolder>() {
    //Card view holder
    inner class CardViewObjectsHolder(view:View):RecyclerView.ViewHolder(view){
        var tvExpenseIncome:TextView
        var tvCategory:TextView
        var tvAmount:TextView
        var cv:CardView
        init {
            cv = view.findViewById(R.id.cvExpenseIncome)
            tvExpenseIncome = view.findViewById(R.id.tvExpenseIncome)
            tvCategory = view.findViewById(R.id.tvCategory)
            tvAmount = view.findViewById(R.id.tvAmount)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewObjectsHolder {
        val design = LayoutInflater.from(context).inflate(R.layout.card_view_expense_income,parent,false)
        return CardViewObjectsHolder(design)
    }

    override fun getItemCount(): Int {
        return expenseIncomeList.size
    }

    override fun onBindViewHolder(holder: CardViewObjectsHolder, position: Int) {
        val expenseIncome = expenseIncomeList[position]
        holder.tvExpenseIncome.text = expenseIncome.expenseIncome
        holder.tvCategory.text = expenseIncome.expenseIncome
        holder.tvAmount.text = "${expenseIncome.amount} $"
        if(expenseIncome.expenseIncome.equals("INCOME")){
            holder.cv.setCardBackgroundColor(Color.rgb(193, 205, 153))
        }
        if(expenseIncome.expenseIncome.equals("EXPENSE")){
            holder.cv.setCardBackgroundColor(Color.rgb(133, 151, 113))
        }
        





    }
}