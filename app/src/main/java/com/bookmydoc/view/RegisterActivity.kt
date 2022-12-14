package com.bookmydoc.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bookmydoc.Constants
import com.bookmydoc.R
import com.bookmydoc.databinding.ActivityRegisterBinding
import com.bookmydoc.model.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val mFireStore = FirebaseFirestore.getInstance()
    private var mProgressDialog: Dialog? = null
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.txtLogin.setOnClickListener(this)
        binding.btnSignup.setOnClickListener(this)
    }

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)

        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@RegisterActivity, R.color.colorSnackBarError)
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@RegisterActivity, R.color.colorSnackBarSuccess)
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
            binding.txtLogin -> {
                onBackPressed()
            }
            binding.btnSignup -> {
                registerUser()

            }
        }
    }
// registering new users with details
    private fun registerUser() {
        if (validateRegisterDetails()) {
            showProgressDialog(getString(R.string.please_wait))

            val email: String = binding.mEdtEmail.text.toString().trim { it <= ' ' }
            val password: String = binding.mEdtPassword.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        val userId = firebaseUser.uid

                        val user = User(userId,
                            binding.mEdtFullName.text.toString().trim { it <= ' ' },
                            binding.mEdtEmail.text.toString().trim { it <= ' ' },
                            binding.mEdtMobile.text.toString().toLong(),
                            binding.spCountry.getSelectedItem().toString().trim { it <= ' ' })

                        //Register the user to the FireStore firebase
                        registerUser(user)
                        //getting details and logged in the user
                        getUserDetails()
                    } else {
                        hideProgressDialog()
                        Toast.makeText(
                            this@RegisterActivity,
                            task.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.addOnFailureListener { exception ->
                    hideProgressDialog()
                    Toast.makeText(this@RegisterActivity, exception.message, Toast.LENGTH_SHORT)
                        .show()
                    exception.printStackTrace()
                }
        }
    }

    fun registerUser(user: User) {
        mFireStore.collection(Constants.USERS)
            .document(user.id)
            .set(user, SetOptions.merge())
            .addOnSuccessListener {
                hideProgressDialog()
                Toast.makeText(
                    this@RegisterActivity,
                    "You are registered successfully",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }.addOnFailureListener { exception ->
                hideProgressDialog()

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


                    hideProgressDialog()
                    val intent = Intent(this@RegisterActivity, WelcomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
                    startActivity(intent)
                    finishAffinity()

                }

            }.addOnFailureListener { exception ->
                hideProgressDialog()

            }
    }

    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.mEdtFullName.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please enter first name.", true)
                false
            }
            TextUtils.isEmpty(binding.mEdtEmail.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please enter an email id.", true)
                false
            }
            TextUtils.isEmpty(binding.mEdtPassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please enter a password.", true)
                false
            }
            TextUtils.isEmpty(binding.mEdtConfirmPassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please enter confirm password.", true)
                false
            }
            binding.mEdtPassword.text.toString()
                .trim { it <= ' ' } != binding.mEdtConfirmPassword.text.toString()
                .trim { it <= ' ' } -> {
                showErrorSnackBar("Password and confirm password does not match", true)
                false
            }
            !binding.cbTermsAndCondition.isChecked -> {
                showErrorSnackBar("Please agree terms and conditions.", true)
                false
            }
            else -> {
                true
            }

        }
    }



    override fun onDestroy() {
        dismissProgressDialog()
        super.onDestroy()

    }
}