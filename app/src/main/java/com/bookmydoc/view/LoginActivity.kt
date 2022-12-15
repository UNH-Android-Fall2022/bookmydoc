package com.bookmydoc.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bookmydoc.Constants
import com.bookmydoc.R
import com.bookmydoc.databinding.ActivityLoginBinding
import com.bookmydoc.model.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var mProgressDialog: Dialog? = null

    private val mFireStore = FirebaseFirestore.getInstance()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.btnLogin.setOnClickListener(this)
        binding.txtSignup.setOnClickListener(this)
        binding.txtDrLogin.setOnClickListener(this)


    }

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)

        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@LoginActivity, R.color.colorSnackBarError)
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@LoginActivity, R.color.colorSnackBarSuccess)
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
            binding.txtSignup -> {
                val intent = Intent(this, RegisterActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this@LoginActivity,
                    (binding.ivProfile as View?)!!, "profile"
                )
                startActivity(intent, options.toBundle())

            }
            binding.btnLogin -> {
                loginUser()
            }
            binding.txtDrLogin -> {
                val intent = Intent(this, DoctorLoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun loginUser() {

        if (validateLoginDetails()) {

            showProgressDialog(getString(R.string.please_wait))

            val email: String = binding.mEdtEmail.text.toString().trim { it <= ' ' }
            val password: String = binding.mEdtPassword.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        getUserDetails(this@LoginActivity)

                    } else {
                        hideProgressDialog()
                        Toast.makeText(
                            this@LoginActivity,
                            task.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.addOnFailureListener { exception ->
                    hideProgressDialog()
                    Toast.makeText(this@LoginActivity, exception.message, Toast.LENGTH_SHORT).show()
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


    fun getUserDetails(activity: Activity) {
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)
                if (user != null) {

                    val sharedPref = activity.getSharedPreferences(
                        Constants.MY_SHOP_PAL_PREFERENCES,
                        Context.MODE_PRIVATE
                    )

                    val editor: SharedPreferences.Editor = sharedPref.edit()
                    editor.putString(
                        Constants.LOGGED_IN_USERNAME,
                        "${user.fullName}"
                    )
                    editor.apply()
                    userLoggedInSuccess(user)

                }

            }.addOnFailureListener { exception ->
                hideProgressDialog()

            }
    }
// validating login credentials
    private fun validateLoginDetails(): Boolean {
        return when {
            TextUtils.isEmpty(binding.mEdtEmail.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please enter an email id.", true)
                false
            }
            TextUtils.isEmpty(binding.mEdtPassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Please enter a password.", true)
                false
            }
            else -> {
                true
            }

        }
    }

    fun userLoggedInSuccess(user: User) {
        hideProgressDialog()

        if (user.profileCompleted == 0) {
            val intent = Intent(this@LoginActivity, UserProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
            startActivity(intent)
        } else {
            val intent = Intent(this@LoginActivity, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        finish()
    }

    override fun onDestroy() {
        dismissProgressDialog()
        super.onDestroy()

    }
}