package com.example.spendy.ui.homepage


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.spendy.R
import com.example.spendy.adapters.ExpenseIncomeAdapter
import com.example.spendy.adapters.HomePageRVAdapter
import com.example.spendy.models.Budget
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_expense_income.*
import kotlinx.android.synthetic.main.fragment_homepage.*
import kotlinx.android.synthetic.main.fragment_statistics_expense_tab.*


class FragmentHomepage : androidx.fragment.app.Fragment() {


    private lateinit var activitiesList: ArrayList<AccountActivity>

    //private lateinit var adapter: HomePageRVAdapter
    private lateinit var adapter: ExpenseIncomeAdapter
    private lateinit var studentList: ArrayList<Entry>
    private lateinit var yearsList: ArrayList<String>

    private val db = Firebase.firestore

    private val auth = FirebaseAuth.getInstance()

    private lateinit var mutableBudgetList: MutableList<Budget>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_homepage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvHomepage.setHasFixedSize(true)
        rvHomepage.layoutManager = LinearLayoutManager(requireContext())


        mutableBudgetList = mutableListOf()

        // adapter = HomePageRVAdapter(requireContext(), activitiesList)

        adapter = ExpenseIncomeAdapter(requireContext(), mutableBudgetList)
        rvHomepage.adapter = adapter


        setPcStatistics()

        printTotalToChart()

        populateRV()


    }


    fun populateRV() {

        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget")
            .limit(5)

            .addSnapshotListener { snapshot, e ->


                if (e != null || snapshot == null) {

                    return@addSnapshotListener
                }


                val budgetList = snapshot.toObjects(Budget::class.java)




                mutableBudgetList.clear()

                mutableBudgetList.addAll(budgetList)

                adapter.notifyDataSetChanged()


            }
    }


    private fun printTotalToChart() {


        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget")

            .addSnapshotListener { snapshot, e ->

                var total = 0.0

                if (e != null || snapshot == null) {

                    return@addSnapshotListener
                }


                val budgetList = snapshot.toObjects(Budget::class.java)


                budgetList.forEach {
                    if (it.type == 0) {
                        total += it.amount
                    } else if (it.type == 1) {
                        total -= it.amount
                    }
                }

                pcHomepage.centerText = total.toString()


                //mutableBudgetList.clear()

                //lateinit var  limitedList: MutableList<Budget>


                /*for(i in 0..4){
                    limitedList.add(budgetList[i])
                }*/

                //mutableBudgetList.addAll(budgetList)

                // adapter.notifyDataSetChanged()


            }


    }

    private fun setPcStatistics() {
        val expenses = ArrayList<PieEntry>()
        expenses.add(PieEntry(500F, "Education"))
        expenses.add(PieEntry(1000F, "Rent"))
        expenses.add(PieEntry(1500F, "Shopping"))
        //expenses.add(PieEntry(800F, "Health"))
        //expenses.add(PieEntry(900F, "Hobbies"))

        val pieDataSet = PieDataSet(expenses, "")
        pieDataSet.valueTextSize = 24f

        val pieData = PieData(pieDataSet)
        pieDataSet.setColors(
            Color.rgb(38, 198, 218), Color.rgb(0, 149, 168), Color.rgb(207, 216, 220)
        )

        pcHomepage.setHoleRadius(60f)
        pcHomepage.setCenterTextSize(26f)
        pcHomepage.setEntryLabelTextSize(18f)
        pcHomepage.data = pieData
        pcHomepage.setUsePercentValues(false)

        pcHomepage.setEntryLabelColor(Color.WHITE)


        /* val legend: Legend = pcStatistics.getLegend()
         legend.form = Legend.LegendForm.CIRCLE
         legend.textSize = 16f
         legend.formSize = 20f
         legend.formToTextSpace = 4f*/
    }
}