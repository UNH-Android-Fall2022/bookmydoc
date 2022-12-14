package com.bookmydoc

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap

object  Constants {

    /*Collections in firebase */
    const val USERS: String = "users"
    const val CATEGORIES : String = "categories"
    const val DOCTOR : String = "doctors"
    const val BOOKING : String = "booking"


    const val MY_SHOP_PAL_PREFERENCES: String = "myShopPalPreferences"
    const val LOGGED_IN_USERNAME: String = "logged_in_user_name"
    const val EXTRA_USER_DETAILS: String = "extra_user_details"
    const val MALE: String = "male"
    const val FEMALE: String = "female"

    const val FULL_NAME : String = "fullName"
    const val LAST_NAME : String = "lastName"
    const val GENDER: String = "gender"
    const val MOBILE: String = "mobile"
    const val IMAGE: String = "image"
    const val USER_ID : String = "user_id"
    const val DOCTOR_ID : String = "doctor_id"
    const val CATEGORIES_ID : String = "categories_id"
    const val RATING : String = "rating"
    const val USER_PROFILE_IMAGE : String = "user_profile_image"
    const val PROFILE_COMPLETED : String = "profileCompleted"
    const val READ_STORAGE_PERMISSION_CODE = 2
    const val PICK_IMAGE_REQUEST_CODE = 1

    fun showImageChooser(activity: Activity) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activity.startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
    }
    fun getFileExtension(activity: Activity,uri : Uri?) : String?{

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(activity.contentResolver.getType(uri!!))

    }
}