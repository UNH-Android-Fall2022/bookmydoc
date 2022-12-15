package com.bookmydoc.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookmydoc.Constants
import com.bookmydoc.R
import com.bookmydoc.adapter.DoctorAdapter
import com.bookmydoc.adapter.MybookingAdapter
import com.bookmydoc.databinding.ActivityMyBookingBinding
import com.bookmydoc.interfaces.ListSelector
import com.bookmydoc.model.Booking
import com.bookmydoc.model.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class MyBookingActivity : AppCompatActivity(), View.OnClickListener {
    private var myBookingAdapyter: MybookingAdapter? = null
    private var doctorArrayList = ArrayList<String>()
    private lateinit var binding: ActivityMyBookingBinding

    private val mFireStore = FirebaseFirestore.getInstance()
    private  var mProgressDialog : Dialog? = null
    private lateinit var mUserDetails: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_booking)
        binding.imgBack.setOnClickListener(this)
        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {
            mUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!}
        getMyBookingList(this, mUserDetails.id)

    }
    // get booking list from firebase
    fun getMyBookingList(activity: MyBookingActivity, uer_id: String) {
        activity.showProgressDialog("")
        mFireStore.collection(Constants.BOOKING)
            .whereEqualTo(Constants.USER_ID, uer_id)
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName, document.documents.toString())

                val productList: ArrayList<Booking> = ArrayList()

                for (i in document.documents) {
                    val product = i.toObject(Booking::class.java)!!
                    product.booking_id = i.id
                    productList.add(product)
                }
                successBookingListFromFirestore(productList)
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e("Error :: ", e.message.toString())
            }
    }
    fun showErrorSnackBar(message : String, errorMessage : Boolean){
        val snackBar = Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_LONG)

        val snackBarView = snackBar.view

        if (errorMessage){
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@MyBookingActivity, R.color.colorSnackBarError)
            )
        }else{
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@MyBookingActivity,R.color.colorSnackBarSuccess)
            )
        }
        snackBar.show()
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
    fun dismissProgressDialog(){
        mProgressDialog?.dismiss()
    }
    override fun onClick(view: View?) {
        when (view) {
            binding.imgBack -> {
                onBackPressed()
            }
        }
    }
    fun successBookingListFromFirestore(doctorArrayList: ArrayList<Booking>) {
        hideProgressDialog()
        Log.e("TAG", "doctorArrayList-" + doctorArrayList.size)
        myBookingAdapyter = MybookingAdapter(object : ListSelector {
            override fun selectedList(position: Int) {

            }

        }, 3)
        binding.rvMorningSlot.layoutManager = LinearLayoutManager(this)
        binding.rvMorningSlot.adapter = myBookingAdapyter


        myBookingAdapyter!!.setUpcomingList(this, doctorArrayList)
    }
}