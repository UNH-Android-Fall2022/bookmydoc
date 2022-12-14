package com.bookmydoc.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bookmydoc.R
import com.bookmydoc.base.BaseActivity
import com.bookmydoc.databinding.ViewBookingTimeBinding
import com.bookmydoc.databinding.ViewCategoryBinding
import com.bookmydoc.interfaces.ListSelector
import com.bookmydoc.model.Categories
import com.bumptech.glide.Glide

class CategoryAdapter(val mCallBack: ListSelector, val mcount: Int) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    public var itemList: ArrayList<Categories>? = null
    private var activity: BaseActivity? = null
    private var count: Int? = mcount

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val mBinding = DataBindingUtil.inflate<ViewCategoryBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.view_category, viewGroup, false
        )
        return ViewHolder(mBinding)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, i: Int) {

        holder.mBinding.txtName.text = itemList!!.get(i).name
        holder.itemView.setOnClickListener {
            mCallBack.selectedList(i)
        }
        Glide.with(activity!!)
            .load(itemList!!.get(i).image)
            .into(holder.mBinding.image)
    }

    override fun getItemCount(): Int {
        return count!!

    }

    fun setUpcomingList(
        activity: BaseActivity,
        itemList: ArrayList<Categories>?
    ) {
        this.itemList = itemList
        this.activity = activity
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mBinding: ViewCategoryBinding) :
        RecyclerView.ViewHolder(mBinding.root)

}