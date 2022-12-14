package com.bookmydoc.view

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.bookmydoc.Constants
import com.bookmydoc.R
import com.bookmydoc.adapter.SlotAdapter
import com.bookmydoc.databinding.ActivityDocotrDetailBinding
import com.bookmydoc.interfaces.ListSelector
import com.bookmydoc.model.Doctors
import java.util.*

import com.bookmydoc.adapter.CustomDropDownAdapter
import com.bookmydoc.model.Booking
import com.bookmydoc.model.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.text.SimpleDateFormat


class DoctorDetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDocotrDetailBinding
    private var mProgressDialog: Dialog? = null
    private var mSlotAdapter: SlotAdapter? = null
    var drId: String = ""
    var selectedTopic: String = ""
    var selectedSlot: String = ""
    var selectedTime: String = ""
    var userName: String = ""
    var cal = Calendar.getInstance()
    private lateinit var mDoctorDetails: Doctors

    private val mFireStore = FirebaseFirestore.getInstance()
    private var timeSlotMorrningList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_docotr_detail)
        binding.btnBook.setOnClickListener(this)
        binding.imgBack.setOnClickListener(this)
        binding.imgCall.setOnClickListener(this)
        binding.imgMap.setOnClickListener(this)
        binding.txtDate.setOnClickListener(this)

        drId = intent.getStringExtra("drId").toString()
        showProgressDialog("")
        geDoctorDetails(this, drId)

        mSlotAdapter = SlotAdapter(object : ListSelector {
            override fun selectedList(position: Int) {

                selectedSlot = mSlotAdapter!!.itemList!!.get(position)

            }

        }, 3)
        var layoutManager3 = GridLayoutManager(this, 4)
        binding!!.rvMorningSlot.layoutManager = layoutManager3
        binding!!.rvMorningSlot.adapter = mSlotAdapter
        timeSlotMorrningList.add("10:00 AM")
        timeSlotMorrningList.add("11:00 AM")
        timeSlotMorrningList.add("12:00 PM")
        timeSlotMorrningList.add("02:00 PM")
        timeSlotMorrningList.add("03:00 PM")
        timeSlotMorrningList.add("04:00 PM")
        mSlotAdapter!!.setUpcomingList(this@DoctorDetailActivity, timeSlotMorrningList)
    }

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)

        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@DoctorDetailActivity, R.color.colorSnackBarError)
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@DoctorDetailActivity, R.color.colorSnackBarSuccess)
            )
        }
        snackBar.show()
    }

    fun geDoctorDetails(activity: Activity, doctorID: String) {

        mFireStore.collection(Constants.DOCTOR)
            .document(doctorID)
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName, document.toString())

                val product = document.toObject(Doctors::class.java)

                if (product != null) {
                    doctorDetailsSuccess(product)

                }
            }
            .addOnFailureListener { e ->
                hideProgressDialog()
                Log.e(activity.javaClass.simpleName, e.message.toString())
            }
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
            binding.btnBook -> {
                if (TextUtils.isEmpty(selectedSlot)) {
                    showErrorSnackBar("Please select slot.", true)
                } else if (TextUtils.isEmpty(selectedTopic)) {
                    showErrorSnackBar("Please choose topic.", true)
                } else {
                    addBooking()
                }

            }
            binding.imgBack -> {
                onBackPressed()
            }
            binding.imgMap -> {
                val geoUri =
                    "http://maps.google.com/maps?q=loc:" + mDoctorDetails.latitude + "," + mDoctorDetails.longitude + " (" + mDoctorDetails.name + ")"

                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(geoUri)
                )
                startActivity(intent)
            }
            binding.imgCall -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + mDoctorDetails.mobile_number)
                startActivity(intent)
            }

            binding.txtDate -> {
                DatePickerDialog(
                    this@DoctorDetailActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }

    val dateSetListener = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(
            view: DatePicker, year: Int, monthOfYear: Int,
            dayOfMonth: Int
        ) {
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "MM/dd/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            selectedTime = sdf.format(cal.getTime())
            binding.txtDate.text = sdf.format(cal.getTime())
        }
    }

    fun doctorDetailsSuccess(doctor: Doctors) {
        mDoctorDetails = doctor
        hideProgressDialog()
        getUserDetails()
        binding.txtDrName.text = doctor.name
        binding.txtCategory.text = doctor.categories
        binding.txtPatient.text = doctor.patient
        binding.txtExperience.text = doctor.experience
        binding.txtRating.text = doctor.rating.toString()


        val customDropDownAdapter = CustomDropDownAdapter(this, doctor.topic)

        binding.mSpinnerCity.adapter = customDropDownAdapter
        binding.mSpinnerCity.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                selectedTopic = doctor.topic.get(position)

            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // code to perform some action
            }
        }


    }

    fun getCurrentUserId(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId
    }


    fun getUserDetails() {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)
                if (user != null) {

                    val sharedPref = getSharedPreferences(
                        Constants.MY_SHOP_PAL_PREFERENCES,
                        Context.MODE_PRIVATE
                    )

                    val editor: SharedPreferences.Editor = sharedPref.edit()
                    editor.putString(
                        Constants.LOGGED_IN_USERNAME,
                        "${user.fullName}"
                    )
                    editor.apply()
                    userDetailSuccess(user)

                }

            }.addOnFailureListener { exception ->
                hideProgressDialog()

            }
    }

    fun addBooking() {
        val booking = Booking(
            System.currentTimeMillis().toString(),
            drId,
            getCurrentUserId(),
            mDoctorDetails.name,
            userName,
            selectedTopic,
            selectedSlot,
            selectedTime
        )
        addBooking(booking)
    }

    fun addBooking(user: Booking) {
        mFireStore.collection(Constants.BOOKING)
            .document(user.booking_id)
            .set(user, SetOptions.merge())
            .addOnSuccessListener {
                val intent = Intent(this, SuccessActivity::class.java)
                intent.putExtra("drName", mDoctorDetails.name)
                startActivity(intent)
            }.addOnFailureListener { exception ->
                hideProgressDialog()
            }

    }


    fun userDetailSuccess(user: User) {

        userName = user.fullName
    }

}