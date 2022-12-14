package com.bookmydoc.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bookmydoc.R
import com.bookmydoc.databinding.ViewDoctorBinding
import com.bookmydoc.interfaces.ListSelector
import com.bookmydoc.model.Doctors

class DoctorAdapter(val mCallBack: ListSelector, val mcount: Int) :
    RecyclerView.Adapter<DoctorAdapter.ViewHolder>() {
    public var itemList: ArrayList<Doctors>? = null
    private var activity: Context? = null
    private var count: Int? = mcount

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val mBinding = DataBindingUtil.inflate<ViewDoctorBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.view_doctor, viewGroup, false
        )
        return ViewHolder(mBinding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, i: Int) {

        holder.mBinding.txtName.text = itemList!!.get(i).name
        holder.mBinding.txtCategory.text = itemList!!.get(i).categories
        holder.mBinding.txtRating.text = itemList!!.get(i).rating.toString()
        holder.itemView.setOnClickListener {
            mCallBack.selectedList(i)
        }

    }

    override fun getItemCount(): Int {
        return if (itemList == null) 0 else itemList!!.size

    }

    fun setUpcomingList(
        activity: Context,
        itemList: ArrayList<Doctors>?
    ) {
        this.itemList = itemList
        this.activity = activity
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mBinding: ViewDoctorBinding) :
        RecyclerView.ViewHolder(mBinding.root)

}