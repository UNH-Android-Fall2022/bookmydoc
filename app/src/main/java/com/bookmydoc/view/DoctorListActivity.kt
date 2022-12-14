package com.bookmydoc.view

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookmydoc.Constants
import com.bookmydoc.R
import com.bookmydoc.adapter.DoctorAdapter
import com.bookmydoc.databinding.ActivityDoctorListBinding
import com.bookmydoc.interfaces.ListSelector
import com.bookmydoc.model.Doctors
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class DoctorListActivity : AppCompatActivity(), View.OnClickListener {

    private var mProgressDialog: Dialog? = null
    private var doctorAdapyter: DoctorAdapter? = null
    private var doctorArrayList = ArrayList<String>()
    private lateinit var binding: ActivityDoctorListBinding
    var categoryName: String = ""
    private val mFireStore = FirebaseFirestore.getInstance()
    var categoryID: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_doctor_list)
        binding.imgBack.setOnClickListener(this)
        categoryName = intent.getStringExtra("category").toString()
        categoryID = intent.getIntExtra("categoryId", 0)
        binding.txtTitle.text = categoryName

        if (categoryID == 0)
          getAllDoctorList( categoryID)
        else
            getDoctorList(categoryID)
    }
    fun getDoctorList( cat_id: Int) {
        mFireStore.collection(Constants.DOCTOR)
            .whereEqualTo(Constants.CATEGORIES_ID, cat_id)
            .get()
            .addOnSuccessListener { document ->

                val productList: ArrayList<Doctors> = ArrayList()

                for (i in document.documents) {
                    val product = i.toObject(Doctors::class.java)!!
                    product.doctor_id = i.id
                    productList.add(product)
                }
                doctorAdapyter = DoctorAdapter(object : ListSelector {
                    override fun selectedList(position: Int) {

                        val intent = Intent(this@DoctorListActivity, DoctorDetailActivity::class.java)
                        intent.putExtra("drId", productList.get(position).doctor_id)
                        startActivity(intent)
                    }

                }, 3)
                binding.rvMorningSlot.layoutManager = LinearLayoutManager(this)
                binding.rvMorningSlot.adapter = doctorAdapyter


                doctorAdapyter!!.setUpcomingList(this, productList)
            }
            .addOnFailureListener { e ->
                hideProgressDialog()
                Log.e("Error :: ", e.message.toString())
            }
    }
    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)

        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@DoctorListActivity, R.color.colorSnackBarError)
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@DoctorListActivity, R.color.colorSnackBarSuccess)
            )
        }
        snackBar.show()
    }

    fun showProgressDialog(message: String) {
        mProgressDialog = Dialog(this)
        mProgressDialog!!.setContentView(R.layout.dialog_progress)
        mProgressDialog!!.findViewById<TextView>(R.id.tv_progress_text).text = message
        mProgressDialog!!.setCancelable(false)
        mProgressDialog!!.setCanceledOnTouchOutside(false)
        mProgressDialog?.show()
    }

    fun hideProgressDialog() {
        mProgressDialog!!.hide()
    }

    fun dismissProgressDialog() {
        mProgressDialog?.dismiss()
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.imgBack -> {
                onBackPressed()
            }
        }
    }

    fun getAllDoctorList(cat_id: Int) {
        mFireStore.collection(Constants.DOCTOR)
            .get()
            .addOnSuccessListener { document ->

                val productList: ArrayList<Doctors> = ArrayList()

                for (i in document.documents) {
                    val product = i.toObject(Doctors::class.java)!!
                    product.doctor_id = i.id
                    productList.add(product)
                }
                doctorAdapyter = DoctorAdapter(object : ListSelector {
                    override fun selectedList(position: Int) {

                        val intent = Intent(this@DoctorListActivity, DoctorDetailActivity::class.java)
                        intent.putExtra("drId", productList.get(position).doctor_id)
                        startActivity(intent)
                    }

                }, 3)
                binding.rvMorningSlot.layoutManager = LinearLayoutManager(this)
                binding.rvMorningSlot.adapter = doctorAdapyter


                doctorAdapyter!!.setUpcomingList(this, productList)

            }
            .addOnFailureListener { e ->
                hideProgressDialog()

                Log.e("Error :: ", e.message.toString())
            }
    }

}