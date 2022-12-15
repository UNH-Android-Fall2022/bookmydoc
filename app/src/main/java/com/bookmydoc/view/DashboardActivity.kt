package com.bookmydoc.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.bookmydoc.Constants
import com.bookmydoc.R
import com.bookmydoc.adapter.CategoryAdapter
import com.bookmydoc.adapter.DoctorAdapter
import com.bookmydoc.databinding.ActivityDashboardBinding
import com.bookmydoc.interfaces.ListSelector
import com.bookmydoc.model.Categories
import com.bookmydoc.model.Doctors
import com.bookmydoc.model.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class DashboardActivity : AppCompatActivity(), View.OnClickListener {
    private var doctorAdapyter: DoctorAdapter? = null
    private var doctorArrayList = ArrayList<Doctors>()
    private lateinit var binding: ActivityDashboardBinding
    private val mFireStore = FirebaseFirestore.getInstance()

    private var categoryAdapter: CategoryAdapter? = null
    private lateinit var categoryArrayList: ArrayList<Categories>
    private var mProgressDialog: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        binding.linPopular.setOnClickListener(this)
        binding.txtViewmore.setOnClickListener(this)
        binding.txtDrViewMore.setOnClickListener(this)
        binding.cardEye.setOnClickListener(this)
        binding.cardBrain.setOnClickListener(this)
        binding.cardDental.setOnClickListener(this)
        binding.cardTopDoctor.setOnClickListener(this)
        binding.imgProfile.setOnClickListener(this)


        getUserDetails()
        getTopDoctorList(this, 4.0)
    }

    fun getTopDoctorList(activity: Activity, cat_id: Double) {
        mFireStore.collection(Constants.DOCTOR)
            .whereGreaterThan(Constants.RATING, cat_id)
            .get()
            .addOnSuccessListener { document ->
                Log.i(activity.javaClass.simpleName, document.documents.toString())

                val productList: ArrayList<Doctors> = ArrayList()

                for (i in document.documents) {
                    val product = i.toObject(Doctors::class.java)!!
                    product.doctor_id = i.id
                    productList.add(product)
                }
                this.doctorArrayList = productList
                doctorAdapyter = DoctorAdapter(object : ListSelector {
                    override fun selectedList(position: Int) {

                        val intent =
                            Intent(this@DashboardActivity, DoctorDetailActivity::class.java)
                        intent.putExtra("drId", doctorArrayList.get(position).doctor_id)
                        startActivity(intent)
                    }

                }, 3)
                binding.rvDoctors.layoutManager = LinearLayoutManager(this)
                binding.rvDoctors.adapter = doctorAdapyter
                doctorAdapyter!!.setUpcomingList(this, doctorArrayList)

                if (doctorArrayList.size > 0) {
                    binding.txtDrName.text = doctorArrayList.get(0).name
                    binding.txtCategory.text = doctorArrayList.get(0).categories
                }

            }
            .addOnFailureListener { e ->
                hideProgressDialog()

            }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.linPopular -> {
                if (doctorArrayList.size > 0) {
                    val intent = Intent(this@DashboardActivity, DoctorDetailActivity::class.java)
                    intent.putExtra("drId", doctorArrayList.get(0).doctor_id)
                    startActivity(intent)
                }

            }
            binding.cardTopDoctor -> {
                val intent = Intent(this, DoctorDetailActivity::class.java)
                intent.putExtra("drName", "Dr. Anastasia Alana")
                startActivity(intent)
            }
            binding.txtViewmore -> {
                val intent = Intent(this, CategoryActivity::class.java)
                startActivity(intent)
            }
            binding.txtDrViewMore -> {
                val intent = Intent(this, DoctorListActivity::class.java)
                intent.putExtra("category", "Top Doctors")
                intent.putExtra("categoryId", 0)
                startActivity(intent)
            }

            binding.imgProfile -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
    }

// retrieving current userid
    fun getCurrentUserId(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId
    }

// retrieving user details and also using shared preferences to store it
    fun getUserDetails() {
        showProgressDialog(getString(R.string.please_wait))
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
                        user.fullName
                    )
                    editor.apply()


                    getCategoriesList()
                    hideProgressDialog()
                    binding.txtUserName.text = user.fullName
                    val imgUri = user.image.toUri().buildUpon().scheme("https").build()
                    binding.imgProfile.load(imgUri)

                }

            }.addOnFailureListener { exception ->
                hideProgressDialog()

            }
    }

// retreiving categories list
    fun getCategoriesList() {
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

                        val intent = Intent(this@DashboardActivity, DoctorListActivity::class.java)
                        intent.putExtra("category", categoryArrayList.get(position).name)
                        intent.putExtra("categoryId", categoryArrayList.get(position).id)
                        startActivity(intent)
                    }

                }, 3)
                val layoutManager3 = GridLayoutManager(this, 3)
                binding.rvMorningSlot.layoutManager = layoutManager3
                binding.rvMorningSlot.adapter = categoryAdapter
                categoryAdapter!!.setUpcomingList(this, categoryArrayList)

            }
            .addOnFailureListener { e ->

                Log.e("Error :: ", e.message.toString())
            }
    }


    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)

        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@DashboardActivity, R.color.colorSnackBarError)
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@DashboardActivity, R.color.colorSnackBarSuccess)
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
}