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
import com.bookmydoc.adapter.DoctorAppoinetmentAdapter
import com.bookmydoc.databinding.ActivityDoctorDashboardBinding
import com.bookmydoc.interfaces.ListSelector
import com.bookmydoc.model.Booking
import com.bookmydoc.model.Doctors
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class DoctorDashboardActivity : AppCompatActivity(), View.OnClickListener {
    private var mProgressDialog: Dialog? = null

    private val mFireStore = FirebaseFirestore.getInstance()
    private var myBookingAdapyter: DoctorAppoinetmentAdapter? = null
    private var doctorArrayList = ArrayList<String>()
    private lateinit var binding: ActivityDoctorDashboardBinding

    private lateinit var mUserDetails: Doctors
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_doctor_dashboard)
        binding.linLogout.setOnClickListener(this)

        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {
            mUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }

        binding.txtUserName.text = mUserDetails.name
        getDoctorBookingList(mUserDetails.doctor_id)
    }

    fun getDoctorBookingList(uer_id: String) {
        showProgressDialog("")
        mFireStore.collection(Constants.BOOKING)
            .whereEqualTo(Constants.DOCTOR_ID, uer_id)
            .get()
            .addOnSuccessListener { document ->

                val productList: ArrayList<Booking> = ArrayList()

                for (i in document.documents) {
                    val product = i.toObject(Booking::class.java)!!
                    product.booking_id = i.id
                    productList.add(product)
                }
                hideProgressDialog()
                Log.e("TAG", "doctorArrayList-" + productList.size)
                myBookingAdapyter = DoctorAppoinetmentAdapter(object : ListSelector {
                    override fun selectedList(position: Int) {

                    }

                }, 3)
                binding.rvDoctors.layoutManager = LinearLayoutManager(this)
                binding.rvDoctors.adapter = myBookingAdapyter
                myBookingAdapyter!!.setUpcomingList(this, productList)
            }
            .addOnFailureListener { e ->
                hideProgressDialog()
            }
    }

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)

        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@DoctorDashboardActivity, R.color.colorSnackBarError)
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@DoctorDashboardActivity, R.color.colorSnackBarSuccess)
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
            binding.linLogout -> {
                startActivity(Intent(this, DoctorLoginActivity::class.java))
                finishAffinity()
            }
        }
    }

}