package com.bookmydoc.view

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import coil.load
import com.bookmydoc.Constants
import com.bookmydoc.R
import com.bookmydoc.databinding.ActivityCategoryBinding
import com.bookmydoc.databinding.ActivityUserProfileBinding
import com.bookmydoc.model.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException

class UserProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserProfileBinding

    private val mFireStore = FirebaseFirestore.getInstance()
    private var mProgressDialog: Dialog? = null
    private lateinit var mUserDetails: User
    private var mSelectedProfileImageFileUri: Uri? = null
    private var mUserProfileImageUrl: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile)

        if (intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {
            mUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!

            binding.mEdtFullName.setText(mUserDetails.fullName)

            binding.mEdtEmail.isEnabled = false
            binding.mEdtEmail.setText(mUserDetails.email)

            if (mUserDetails.profileCompleted == 0) {
                binding.txtTitle.text = getString(R.string.title_complete_profile)

                binding.mEdtFullName.isEnabled = false

            } else {

                binding.txtTitle.text = getString(R.string.edit_profile)
                //to build image in https
                val imgUri = mUserDetails.image.toUri().buildUpon().scheme("https").build()
                binding.imgProfile.load(imgUri)



                if (mUserDetails.mobile != 0L) {
                    binding.mEdtMobile.setText(mUserDetails.mobile.toString())
                }

                if (mUserDetails.gender == Constants.MALE) {
                    binding.rbMale.isChecked = true
                } else {
                    binding.rbFemale.isChecked = true
                }

            }
        }
        binding.imgPic.setOnClickListener(this)
        binding.btnUpdate.setOnClickListener(this)
    }

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)

        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@UserProfileActivity, R.color.colorSnackBarError)
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@UserProfileActivity, R.color.colorSnackBarSuccess)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.PICK_IMAGE_REQUEST_CODE) {
            if (data!!.data != null) {
                try {
                    mSelectedProfileImageFileUri = data.data

                    val imgUri = mSelectedProfileImageFileUri!!.buildUpon().scheme("https").build()
                    binding.imgProfile.load(imgUri)


                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(
                        this,
                        getString(R.string.image_selection_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Constants.showImageChooser(this)
        } else {
            Toast.makeText(
                this,
                getString(R.string.read_storage_permission_denied),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.imgPic -> {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    Constants.showImageChooser(this)
                } else {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        Constants.READ_STORAGE_PERMISSION_CODE
                    )
                }

            }
            R.id.btnUpdate -> {
                if (validateUserProfileData()) {

                    showProgressDialog(getString(R.string.please_wait))

                    if (mSelectedProfileImageFileUri != null) {

                        uploadImageToCloudStorage(
                            mSelectedProfileImageFileUri,
                            Constants.USER_PROFILE_IMAGE
                        )
                    } else {
                        updateUserProfileDetails()
                    }
                }

            }
        }
    }

    fun uploadImageToCloudStorage(imageFileUri: Uri?, imageType: String) {
        val shrf: StorageReference = FirebaseStorage.getInstance().reference.child(
            imageType
                    + System.currentTimeMillis() + "."
                    + Constants.getFileExtension(this, imageFileUri)
        )

        shrf.putFile(imageFileUri!!)
            .addOnSuccessListener { snapShot ->
                Log.e("Firebase Image Url", snapShot.metadata!!.reference!!.downloadUrl.toString())
                snapShot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->
                        Log.e("Image Url", uri.toString())
                        mUserProfileImageUrl = uri.toString()

                        updateUserProfileDetails()

                    }
            }.addOnFailureListener { e ->
                hideProgressDialog()

                Log.e("Error while uploading", "Error while uploading image to db", e)
            }
    }


    private fun updateUserProfileDetails() {

        val userHashMap: HashMap<String, Any> = HashMap()

        if (mUserDetails.fullName != binding.mEdtFullName.text.toString().trim { it <= ' ' }) {
            userHashMap[Constants.FULL_NAME] =
                binding.mEdtFullName.text.toString().trim { it <= ' ' }
        }


        val mobileNumber = binding.mEdtMobile.text.toString().trim { it <= ' ' }

        val gender = if (binding.rbMale.isChecked) {
            Constants.MALE
        } else {
            Constants.FEMALE
        }
        if (mUserProfileImageUrl.isNotEmpty()) {
            userHashMap[Constants.IMAGE] = mUserProfileImageUrl
        }

        if (mobileNumber.isNotEmpty() && mobileNumber != mUserDetails.mobile.toString()) {
            userHashMap[Constants.MOBILE] = mobileNumber.toLong()
        }

        if (gender.isNotEmpty() && gender != mUserDetails.gender) {
            userHashMap[Constants.GENDER] = gender
        }
        userHashMap[Constants.PROFILE_COMPLETED] = 1
        userHashMap[Constants.GENDER] = gender

        updateUserProfileData(userHashMap)

    }

    fun getCurrentUserId(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId
    }

    fun updateUserProfileData(userHashMap: HashMap<String, Any>) {

        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserId())
            .update(userHashMap)
            .addOnSuccessListener {
                hideProgressDialog()
                Toast.makeText(this, getString(R.string.profile_update_success_message), Toast.LENGTH_SHORT)
                    .show()

                startActivity(Intent(this, DashboardActivity::class.java))
                finish()

            }.addOnFailureListener { e ->
                hideProgressDialog()


            }
    }


    private fun validateUserProfileData(): Boolean {
        return when {
            TextUtils.isEmpty(binding.mEdtMobile.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(getString(R.string.error_mobile_number), true)
                false
            }
            else -> {
                true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissProgressDialog()
    }


}