package com.example.spendy.ui.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spendy.R
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.fragment_statistics_expense_tab.*


class FragmentExpense : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics_expense_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setpcStatistics()
    }
    private fun setpcStatistics() {
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
            Color.rgb(38,198,218), Color.rgb(0,149,168)
            , Color.rgb(207,216,220), Color.GRAY,Color.MAGENTA,Color.YELLOW
        )

        pcStatistics.setHoleRadius(60f)
        pcStatistics.setCenterTextSize(26f)
        pcStatistics.setEntryLabelTextSize(18f)
        pcStatistics.data = pieData
        pcStatistics.setUsePercentValues(false)
        pcStatistics.centerText = "Total \n 2000"
        pcStatistics.setEntryLabelColor(Color.WHITE)
        pcStatistics.description.text=""

        val legend: Legend = pcStatistics.getLegend()
        legend.form = Legend.LegendForm.CIRCLE
        legend.textSize = 16f
        legend.formSize = 20f
        legend.formToTextSpace = 4f
    }




}