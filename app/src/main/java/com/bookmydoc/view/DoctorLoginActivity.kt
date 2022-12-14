package com.bookmydoc.view

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bookmydoc.Constants
import com.bookmydoc.R
import com.bookmydoc.databinding.ActivityDocotrLoginBinding
import com.bookmydoc.model.Doctors
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class DoctorLoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDocotrLoginBinding
    private lateinit var mDoctorDetails: Doctors


    private val mFireStore = FirebaseFirestore.getInstance()
    private var mProgressDialog: Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_docotr_login)
        binding.btnLogin.setOnClickListener(this)
    }

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)

        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@DoctorLoginActivity, R.color.colorSnackBarError)
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@DoctorLoginActivity, R.color.colorSnackBarSuccess)
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

    override fun onClick(view: View?) {
        when (view) {
            binding.btnLogin -> {
                loginDoctor()
            }

        }
    }

    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.mEdtEmail.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please enter an email id.", true)
                false
            }
            else -> {
                true
            }

        }
    }

    private fun loginDoctor() {

        if (validateLoginDetails()) {

            showProgressDialog(getString(R.string.please_wait))
            val drId: String = binding.mEdtEmail.text.toString().trim { it <= ' ' }

            geDoctorDetails( drId)
        }
    }


    fun geDoctorDetails(doctorID: String) {

        mFireStore.collection(Constants.DOCTOR)
            .document(doctorID)
            .get()
            .addOnSuccessListener { document ->

                val product = document.toObject(Doctors::class.java)

                if (product != null) {
                    mDoctorDetails = product
                    hideProgressDialog()
                    val intent = Intent(this@DoctorLoginActivity, DoctorDashboardActivity::class.java)
                    intent.putExtra(Constants.EXTRA_USER_DETAILS, mDoctorDetails)
                    startActivity(intent)

                }
            }
            .addOnFailureListener { e ->
                hideProgressDialog()

            }
    }


}