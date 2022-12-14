package com.bookmydoc.view

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.bookmydoc.Constants
import com.bookmydoc.R
import com.bookmydoc.adapter.CategoryAdapter
import com.bookmydoc.adapter.SlotAdapter
import com.bookmydoc.databinding.ActivityCategoryBinding
import com.bookmydoc.databinding.ActivityDocotrDetailBinding
import com.bookmydoc.interfaces.ListSelector
import com.bookmydoc.model.Categories
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class CategoryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCategoryBinding
    private var categoryAdapter: CategoryAdapter? = null

    private val mFireStore = FirebaseFirestore.getInstance()
    private  var mProgressDialog : Dialog? = null
    private lateinit var categoryArrayList: ArrayList<Categories>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category)
        binding.imgBack.setOnClickListener(this)

        getCategoriesList()
    }

    fun showProgressDialog(message : String){
        mProgressDialog = Dialog(this)
        mProgressDialog!!.setContentView(R.layout.dialog_progress)
        mProgressDialog!!.findViewById<TextView>(R.id.tv_progress_text).text = message
        mProgressDialog!!.setCancelable(false)
        mProgressDialog!!.setCanceledOnTouchOutside(false)
        mProgressDialog?.show()
    }
    fun hideProgressDialog(){
        mProgressDialog!!.hide()
    }

    fun getCategoriesList() {
        showProgressDialog(getString(R.string.please_wait))
        mFireStore.collection(Constants.CATEGORIES)
            .get()
            .addOnSuccessListener { document ->

                val productList: ArrayList<Categories> = ArrayList()

                for (i in document.documents) {
                    val product = i.toObject(Categories::class.java)!!
                    //product.categories_id = i.id

                    productList.add(product)
                }
                hideProgressDialog()
                categoryArrayList = productList
                categoryAdapter = CategoryAdapter(object : ListSelector {
                    override fun selectedList(position: Int) {

                        val intent = Intent(this@CategoryActivity, DoctorListActivity::class.java)
                        intent.putExtra("category", categoryArrayList.get(position).name)
                        intent.putExtra("categoryId", categoryArrayList.get(position).id)
                        startActivity(intent)
                    }

                }, categoryArrayList.size)
                val layoutManager3 = GridLayoutManager(this, 3)
                binding.rvMorningSlot.layoutManager = layoutManager3
                binding.rvMorningSlot.adapter = categoryAdapter
                categoryAdapter!!.setUpcomingList(this, categoryArrayList)
            }
            .addOnFailureListener { e ->
               hideProgressDialog()

            }
    }


    override fun onClick(view: View?) {
        when (view) {
            binding.imgBack -> {
                onBackPressed()
            }
        }
    }
}