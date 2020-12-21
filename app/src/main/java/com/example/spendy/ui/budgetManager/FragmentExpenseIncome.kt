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
import com.example.spendy.models.Budget
import com.example.spendy.models.ExpenseIncome
import com.example.spendy.repository.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.fragment_expense_income.*


//
// Fragment for Expense and Income Page
class FragmentExpenseIncome : Fragment() {


    private lateinit var expenseIncomeArrayList: ArrayList<Budget>

    private lateinit var adapter: ExpenseIncomeAdapter

    private val categories = ArrayList<String>()

    private lateinit var categoriesAdapter: ArrayAdapter<String>

    private val repository = Repository()

    private val db = Firebase.firestore

    private val auth = FirebaseAuth.getInstance()

    private lateinit var mutableBudgetList: MutableList<Budget>

    //OnCreateView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_expense_income, container, false)
    }


    //onViewCreated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoriesToSpinner()



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

            val category = "${categories[spnCategories.selectedItemPosition]}"



            total += txtAmount.text.toString().toDouble()

            tvTotalAmountShower.text = "TOTAL    " + total.toString() + "  $"


            val budget = Budget(0,txtAmount.text.toString().toDouble(), "${categories[spnCategories.selectedItemPosition]}", "10")

            repository.addIncome(budget)


        }


        // When EXPENSE button clicked add datas to recyclervier
        btnExpense.setOnClickListener {

            ivVallet.visibility = View.GONE

            rvExpenseIncome.visibility = View.VISIBLE

            val amount = txtAmount.text.toString().toInt()

            val category = "${categories[spnCategories.selectedItemPosition]}"


            total -= txtAmount.text.toString().toInt()

            tvTotalAmountShower.text = "TOTAL    " + total.toString() + "  $"




            //Firebase
            val budget = Budget(1,txtAmount.text.toString().toDouble()*-1, "${categories[spnCategories.selectedItemPosition]}", "10")

            repository.addExpense(budget)


            //E mail login den sonra tutulup buraya verilecek
            //repository.addMoney(requireContext(),Money(0,amount.toInt(),0,0,repository.getUser(requireContext(),"email")))
        }


    }


    fun populateRV(){

        db.collection("Users").document(auth.currentUser!!.email.toString()).collection("Budget")
                .addSnapshotListener { snapshot, e ->


                    if (e != null || snapshot == null) {

                        return@addSnapshotListener
                    }


                    val  budgetList = snapshot.toObjects(Budget::class.java)

                    mutableBudgetList.clear()
                    mutableBudgetList.addAll(budgetList)
                    adapter.notifyDataSetChanged()


                }
    }
    //add categories to spinner
    private fun categoriesToSpinner() {
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

        categoriesAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, android.R.id.text1, categories)
        spnCategories.adapter = categoriesAdapter

    }
}