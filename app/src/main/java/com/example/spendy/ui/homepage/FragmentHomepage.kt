package com.example.spendy.ui.homepage


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.spendy.R
import com.example.spendy.adapters.ExpenseIncomeAdapter
import com.example.spendy.models.Budget
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_homepage.*


class FragmentHomepage : androidx.fragment.app.Fragment() {

    private lateinit var adapter: ExpenseIncomeAdapter

    private val db = Firebase.firestore

    private val auth = FirebaseAuth.getInstance()

    private lateinit var mutableBudgetList: MutableList<Budget>

    private val pcEntries = ArrayList<PieEntry>()

    var total = 0.0

    var balance = 0F

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


        adapter = ExpenseIncomeAdapter(requireContext(), mutableBudgetList)
        rvHomepage.adapter = adapter


        populateRV()


    }

    //Populating Rv For Homepage

    private fun populateRV() {


        db.collection("Users").document(auth.currentUser?.email.toString())
            .collection("Budget").orderBy("time", Query.Direction.DESCENDING)

            .addSnapshotListener { snapshot, e ->


                if (e != null || snapshot == null) {

                    return@addSnapshotListener
                }


                val budgetList = snapshot.toObjects(Budget::class.java)

                if (total == 0.0) {
                    budgetList.forEach {
                        if (it.type == 0) {
                            total += it.amount
                        } else if (it.type == 1) {
                            total -= it.amount
                        }
                    }
                    if (budgetList.isEmpty()) {
                        balance = 1F
                    }
                    setPcStatistics()
                }


                mutableBudgetList.clear()

                mutableBudgetList.addAll(budgetList)

                adapter.notifyDataSetChanged()

            }
    }


    //Setting Pie Chart Configuration
    private fun setPcStatistics() {


        balance += total.toString().toFloat()
        pcHomepage.centerText = total.toString()
        pcEntries.add(PieEntry(balance, getString(R.string.balance)))


        val pieDataSet = PieDataSet(pcEntries, "")
        pieDataSet.valueTextSize = 24f

        val pieData = PieData(pieDataSet)
        pieDataSet.setColors(
            Color.rgb(38, 198, 218), Color.rgb(0, 149, 168)
            , Color.rgb(207, 216, 220), Color.GRAY, Color.CYAN
        )

        pcHomepage.setHoleRadius(60f)
        pcHomepage.setCenterTextSize(26f)
        pcHomepage.setEntryLabelTextSize(18f)
        pcHomepage.data = pieData
        pcHomepage.description.text = ""


        pcHomepage.setUsePercentValues(false)

        pcHomepage.setEntryLabelColor(Color.BLACK)

        pcHomepage.getLegend().setEnabled(false)

    }


}