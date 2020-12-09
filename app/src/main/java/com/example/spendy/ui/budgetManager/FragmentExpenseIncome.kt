package com.example.spendy.ui.budgetManager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spendy.adapters.ExpenseIncomeAdapter
import com.example.spendy.R
import com.example.spendy.repository.Repository

import kotlinx.android.synthetic.main.fragment_expense_income.*
// Fragment for Expense and Income Page
class FragmentExpenseIncome:Fragment() {


    private lateinit var expenseIncomeArrayList:ArrayList<ExpenseIncome>
    private lateinit var adapter: ExpenseIncomeAdapter
    private val categories = ArrayList<String>()
    private lateinit var categoriesAdapter:ArrayAdapter<String>
    private val repository = Repository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_expense_income,container,false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoriesToSpinner()
        rvExpenseIncome.setHasFixedSize(true)
        rvExpenseIncome.layoutManager = LinearLayoutManager(requireContext())
        expenseIncomeArrayList = ArrayList<ExpenseIncome>()
        var total = 0.0


        // When INCOME button clicked add datas to recyclervier
        btnIncome.setOnClickListener {

            ivVallet.visibility = View.GONE
            rvExpenseIncome.visibility = View.VISIBLE

            expenseIncomeArrayList.add(ExpenseIncome(0, txtAmount.text.toString().toDouble(), "${categories[spnCategories.selectedItemPosition]}"))
            total+= txtAmount.text.toString().toDouble()
            tvTotalAmountShower.text ="TOTAL    "+total.toString()+"  $"
            adapter = ExpenseIncomeAdapter(requireContext(), expenseIncomeArrayList)
            rvExpenseIncome.adapter = adapter
        }


        // When EXPENSE button clicked add datas to recyclervier
        btnExpense.setOnClickListener {

            ivVallet.visibility = View.GONE

            rvExpenseIncome.visibility = View.VISIBLE

            val amount = txtAmount.text.toString().toDouble()

            expenseIncomeArrayList.add(ExpenseIncome(1, amount, "${categories[spnCategories.selectedItemPosition]}"))

            total-= txtAmount.text.toString().toDouble()

            tvTotalAmountShower.text ="TOTAL    "+total.toString()+"  $"

            adapter = ExpenseIncomeAdapter(requireContext(), expenseIncomeArrayList)

            rvExpenseIncome.adapter = adapter


            //E mail login den sonra tutulup buraya verilecek
            //repository.addMoney(requireContext(),Money(0,amount.toInt(),0,0,repository.getUser(requireContext(),"email")))
        }

        
    }


    //add categories to spinner
    fun categoriesToSpinner(){
        categories.add("SELECT CATEGORY")
        categories.add("BOOK")
        categories.add("CAR")
        categories.add("CHILD")
        categories.add("CLOTHES")
        categories.add("COSMETIC")
        categories.add("CREDIT CARD")
        categories.add("DONATION")
        categories.add("DUES")
        categories.add("EDUCATION")
        categories.add("ELECTRICITY")
        categories.add("ENTERTAINMENT")
        categories.add("FOOD")
        categories.add("HEALTH")
        categories.add("HEAT")
        categories.add("HOUSE")
        categories.add("INSURANCE")
        categories.add("INTERNET")
        categories.add("INVOICE")
        categories.add("MARKET")
        categories.add("PET")
        categories.add("RENTAL FEE")
        categories.add("SALARY")
        categories.add("SALES")
        categories.add("TELEPHONE")
        categories.add("TRANSPORTATION")
        categories.add("TRAVEL")
        categories.add("TV")
        categories.add("WATER")

        categoriesAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,android.R.id.text1,categories)
        spnCategories.adapter = categoriesAdapter

    }
}