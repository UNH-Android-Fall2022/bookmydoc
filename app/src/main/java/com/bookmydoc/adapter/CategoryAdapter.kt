package com.bookmydoc.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bookmydoc.R
import com.bookmydoc.databinding.ViewCategoryBinding
import com.bookmydoc.interfaces.ListSelector
import com.bookmydoc.model.Categories

class CategoryAdapter(val mCallBack: ListSelector, val mcount: Int) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    public var itemList: ArrayList<Categories>? = null
    private var activity: Context? = null
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
        val imgUri = itemList!!.get(i).image.toUri().buildUpon().scheme("https").build()
        holder.mBinding.image.load(imgUri)

    }

    override fun getItemCount(): Int {
        return count!!

    }

    fun setUpcomingList(
        activity: Context,
        itemList: ArrayList<Categories>?
    ) {
        this.itemList = itemList
        this.activity = activity
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mBinding: ViewCategoryBinding) :
        RecyclerView.ViewHolder(mBinding.root)

}