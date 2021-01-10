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
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_statistics_expense_tab.*

class FragmentExpense : Fragment() {

    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

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
        //expenses.add(PieEntry(800F, "Health"))
        //expenses.add(PieEntry(900F, "Hobbies"))

        var valueCredit =0F
        var valueInvoice =0F
        var valueFee =0F
        var valueMarket =0F
        var valueOthers =0F




        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    if(document.data.get("type").toString().toInt()==1) {

                        if (document.data.get("category").toString().equals(getString(R.string.credit_card))){
                           valueCredit+=document.data.get("amount").toString().toFloat()
                        }
                        else if (document.data.get("category").toString().equals( getString(R.string.invoice))){
                            valueInvoice+=document.data.get("amount").toString().toFloat()
                        }

                        else if (document.data.get("category").toString().equals(getString(R.string.rental_fee))){
                            valueFee+=document.data.get("amount").toString().toFloat()
                        }
                        if (document.data.get("category").toString().equals(getString(R.string.market))){
                            valueMarket+=document.data.get("amount").toString().toFloat()
                        }
                        else{
                            valueOthers+=document.data.get("amount").toString().toFloat()
                        }




                    }

                }

                if(valueCredit>0){
                    expenses.add(PieEntry(valueCredit, getString(R.string.credit_card)))
                }
                if(valueInvoice>0){
                    expenses.add( PieEntry(valueInvoice, getString(R.string.invoice)))
                }
                if(valueFee>0){
                    expenses.add( PieEntry(valueFee, getString(R.string.rental_fee)))
                }
                if(valueMarket>0){
                    expenses.add( PieEntry(valueMarket, getString(R.string.market)))
                }
                if(valueOthers>0){
                    expenses.add( PieEntry(valueOthers, "Others"))
                }





                expenses.forEach {

                    println(it.label)
                }

                val pieDataSet = PieDataSet(expenses, "")
                pieDataSet.valueTextSize = 24f

                val pieData = PieData(pieDataSet)
                pieDataSet.setColors(
                    Color.rgb(38,198,218), Color.rgb(0,149,168)
                    , Color.rgb(207,216,220),Color.rgb(207,216,240),Color.rgb(207,216,200)
                )

               // pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS,255)

                pcStatistics.setHoleRadius(60f)
                pcStatistics.setCenterTextSize(26f)
                pcStatistics.setEntryLabelTextSize(18f)
                pcStatistics.data = pieData
                pcStatistics.setUsePercentValues(false)
                pcStatistics.setEntryLabelColor(Color.WHITE)

             /*   val legend: Legend = pcStatistics.getLegend()
                legend.form = Legend.LegendForm.CIRCLE
                legend.textSize = 16f
                legend.formSize = 20f
                legend.formToTextSpace = 4f*/

            }


    }




}