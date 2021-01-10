package com.example.spendy.ui.budgetManager

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendy.adapters.ExpenseIncomeAdapter
import com.example.spendy.R
import com.example.spendy.models.Budget
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.fragment_expense_income.*
import kotlin.collections.ArrayList


// Fragment for Expense and Income Page
class FragmentExpenseIncome : Fragment() {


    private lateinit var expenseIncomeArrayList: ArrayList<Budget>

    private lateinit var adapter: ExpenseIncomeAdapter


    private val db = Firebase.firestore

    private val auth = FirebaseAuth.getInstance()

    private lateinit var mutableBudgetList: MutableList<Budget>


    //OnCreateView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_expense_income, container, false)
    }


    //onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mutableBudgetList = mutableListOf()

        ivVallet.visibility = View.GONE

        rvExpenseIncome.visibility = View.VISIBLE

        adapter = ExpenseIncomeAdapter(requireContext(), mutableBudgetList)

        rvExpenseIncome.adapter = adapter

        rvExpenseIncome.setHasFixedSize(true)

        rvExpenseIncome.layoutManager = LinearLayoutManager(requireContext())

        expenseIncomeArrayList = ArrayList<Budget>()

        var total = 0.0


        populateRV()


        // When INCOME button clicked add datas to recyclervier
        btnIncome.setOnClickListener {

            ivVallet.visibility = View.GONE

            rvExpenseIncome.visibility = View.VISIBLE


            if (!txtAmount.text.toString().isEmpty()) {

                val newIntent = Intent(requireContext(), CategoryActivity::class.java)
                newIntent.putExtra("type", 0)
                newIntent.putExtra("amount", txtAmount.text.toString().toDouble())

                startActivity(newIntent)
            } else {
                return@setOnClickListener
            }


        }


        // When EXPENSE button clicked add datas to recyclervier
        btnExpense.setOnClickListener {

            ivVallet.visibility = View.GONE

            rvExpenseIncome.visibility = View.VISIBLE


            val newIntent = Intent(requireContext(), CategoryActivity::class.java)
            newIntent.putExtra("type", 1)
            newIntent.putExtra("amount", txtAmount.text.toString().toDouble())

            startActivity(newIntent)

        }


    }

    // Take budgets from Firestore continously
    private fun populateRV() {

        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget")
            .orderBy("time", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, e ->

                var total = 0.0
                if (e != null || snapshot == null) {

                    return@addSnapshotListener
                }
                for (i in mutableBudgetList.indices) {

                    mutableBudgetList.get(0).time.toString()

                }

                val budgetList = snapshot.toObjects(Budget::class.java)

                budgetList.forEach {
                    if (it.type == 0) {
                        total += it.amount
                    } else if (it.type == 1) {
                        total -= it.amount
                    }
                }


                tvTotal.text = total.toString()

                mutableBudgetList.clear()
                mutableBudgetList.addAll(budgetList)
                adapter.notifyDataSetChanged()


            }
    }


}