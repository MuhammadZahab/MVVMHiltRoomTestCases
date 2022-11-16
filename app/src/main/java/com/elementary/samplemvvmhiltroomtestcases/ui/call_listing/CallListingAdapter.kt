package com.elementary.samplemvvmhiltroomtestcases.ui.call_listing

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.elementary.samplemvvmhiltroomtestcases.R
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.models.CallListingModel
import com.elementary.samplemvvmhiltroomtestcases.databinding.CallListingItemBinding


class CallListingAdapter(private val dataSet:ArrayList<CallListingModel>):
    RecyclerView.Adapter<CallListingAdapter.CallListingViewHolder>(){



    class CallListingViewHolder(var binding:CallListingItemBinding): RecyclerView.ViewHolder(binding.root) {

        val tv_name:TextView
        val tv_number:TextView


        init {
            // Define click listener for the ViewHolder's View.
            tv_name = binding.tvName
            tv_number = binding.tvNumber

        }

        fun bind(callListingModel: CallListingModel) {
            binding.setVariable(BR.model,callListingModel)
            binding.executePendingBindings()
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallListingViewHolder {
        val binding: CallListingItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.call_listing_item, parent, false)

        return CallListingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CallListingViewHolder, position: Int) {
        var callListingModel: CallListingModel = dataSet.get(position)
        holder.bind(callListingModel)

    }

    override fun getItemCount(): Int {
       return dataSet.size
    }


}