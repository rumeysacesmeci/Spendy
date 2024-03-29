package com.example.spendy.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.spendy.R
import com.example.spendy.models.Budget


// Adapter for card_view_expense_income.xml
class ExpenseIncomeAdapter(
    private val context: Context,
    private val expenseIncomeList: List<Budget>
) : RecyclerView.Adapter<ExpenseIncomeAdapter.CardViewObjectsHolder>() {

    //Card view holder
    inner class CardViewObjectsHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvCategory: TextView
        var tvAmount: TextView
        var cv: CardView

        init {
            cv = view.findViewById(R.id.cvExpenseIncome)
            tvCategory = view.findViewById(R.id.tvCategory)
            tvAmount = view.findViewById(R.id.tvAmount)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewObjectsHolder {
        val design =
            LayoutInflater.from(context).inflate(R.layout.card_view_expense_income, parent, false)
        return CardViewObjectsHolder(design)
    }

    override fun getItemCount(): Int {
        return expenseIncomeList.size
    }

    override fun onBindViewHolder(holder: CardViewObjectsHolder, position: Int) {

        val expenseIncome = expenseIncomeList[position]

        //Values Of CardView Elements
        holder.tvCategory.text = expenseIncome.category
        holder.tvAmount.text = expenseIncome.amount.toString()


        //Controlling for type of Budget(0:Income)
        if (expenseIncome.type == 0) {

            holder.cv.setCardBackgroundColor(Color.rgb(207, 216, 220))
            holder.tvAmount.setTextColor(Color.rgb(0, 128, 0))
            holder.tvAmount.setText("+" + expenseIncome.amount.toString())
        }
        //Controlling for type of Budget(1:Expense)
        if (expenseIncome.type == 1) {

            holder.cv.setCardBackgroundColor(Color.rgb(207, 216, 220))
            holder.tvAmount.setTextColor(Color.RED)
            holder.tvAmount.setText("-" + expenseIncome.amount.toString())

        }


    }


}