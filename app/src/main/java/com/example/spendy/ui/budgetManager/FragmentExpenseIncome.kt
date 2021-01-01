package com.example.spendy.ui.budgetManager

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendy.adapters.ExpenseIncomeAdapter
import com.example.spendy.R
import com.example.spendy.models.Budget
import com.example.spendy.repository.*
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.fragment_expense_income.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


//
// Fragment for Expense and Income Page
class FragmentExpenseIncome : Fragment(), DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {


    private lateinit var expenseIncomeArrayList: ArrayList<Budget>

    private lateinit var adapter: ExpenseIncomeAdapter


    private val repository = Repository()

    private val db = Firebase.firestore

    private val auth = FirebaseAuth.getInstance()

    private lateinit var mutableBudgetList: MutableList<Budget>



    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    //OnCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_expense_income, container, false)
    }


    //onViewCreated

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentTime()



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



        pickDate()




        // When INCOME button clicked add datas to recyclervier
        btnIncome.setOnClickListener {

            ivVallet.visibility = View.GONE

            rvExpenseIncome.visibility = View.VISIBLE






             if(!txtAmount.text.toString().isEmpty()){

                 val newIntent = Intent(requireContext(), CategoryActivity::class.java)
                 newIntent.putExtra("type", 0)
                 newIntent.putExtra("amount", txtAmount.text.toString().toDouble())
                 newIntent.putExtra("time", tvTime.text)
                 startActivity(newIntent)
             }
            else{
                 return@setOnClickListener
             }






        }


        // When EXPENSE button clicked add datas to recyclervier
        btnExpense.setOnClickListener {

            ivVallet.visibility = View.GONE

            rvExpenseIncome.visibility = View.VISIBLE


            total -= txtAmount.text.toString().toDouble()

            tvTotalAmountShower.text = "TOTAL    " + total.toString() + "  $"


            val newIntent = Intent(requireContext(),CategoryActivity::class.java)
            newIntent.putExtra("type",1)
            newIntent.putExtra("amount",txtAmount.text.toString().toDouble())
            newIntent.putExtra("time",tvTime.text)
            startActivity(newIntent)

            //E mail login den sonra tutulup buraya verilecek
            //repository.addMoney(requireContext(),Money(0,amount.toInt(),0,0,repository.getUser(requireContext(),"email")))
        }


    }




    // Take budgets from Firestore continously
    fun populateRV(){

        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget")
                .addSnapshotListener { snapshot, e ->

                    var total =0.0
                    if (e != null || snapshot == null) {

                        return@addSnapshotListener
                    }
                    for (i in mutableBudgetList.indices){

                        mutableBudgetList.get(0).time.toString()

                    }

                    val  budgetList = snapshot.toObjects(Budget::class.java)

                    budgetList.forEach{
                        if(it.type== 0){
                            total += it.amount
                        }
                        else if(it.type ==1){
                            total -= it.amount
                        }
                    }

                    tvTotalAmountShower.text = total.toString()

                    mutableBudgetList.clear()
                    mutableBudgetList.addAll(budgetList)
                    adapter.notifyDataSetChanged()


                }
    }




    private fun getDateTimeCalendar(){
        val cal:Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)

    }





    //When you click time textview this method called firs
    private fun pickDate(){
        tvTime.setOnClickListener {
            getDateTimeCalendar()

            val a1 =DatePickerDialog(requireContext(),this,year,month,day)

            a1.show()


        }
    }





    // Date setter for Datepicker
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()

        TimePickerDialog(requireContext(),this,hour,minute,true).show()

    }





    // Time setter for Timepicker
    @SuppressLint("SetTextI18n")
    override fun onTimeSet(p0: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute
        tvTime.text = "$savedDay-$savedMonth-$savedYear $savedHour:$savedMinute"
    }






    // Function that gave current time as format "dd-MM-yyyy HH:mm"
    @SuppressLint("NewApi")
    fun currentTime() {
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        val formatted = current.format(formatter)

        tvTime.text = formatted.toString()
    }





}