package com.elementary.samplemvvmhiltroomtestcases.ui.call_listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.elementary.samplemvvmhiltroomtestcases.R
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.models.CallListingModel
import com.elementary.samplemvvmhiltroomtestcases.databinding.ActivityCallListingBinding
import com.elementary.samplemvvmhiltroomtestcases.di.NetworkModule
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
typealias lambda = NetworkModule

@AndroidEntryPoint
class CallListingActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCallListingBinding
    private lateinit var recyclerView: RecyclerView


    private lateinit var  callListingAdapter:CallListingAdapter
    private var list_of_data = ArrayList<CallListingModel>()
    private lateinit var callListingViewModel:CallListingViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callListingViewModel = ViewModelProvider(this).get(CallListingViewModel::class.java)
         binding  =   DataBindingUtil.setContentView(this, R.layout.activity_call_listing)


        setViews()
        setAdapter()

//        callListingViewModel.getCallingList()
        callListingViewModel.getCallingListFromDatabase()
        observeCallListing()


        // higher-order function
        fun higherfunc( lambda: lambda ) {     // accepting lambda as parameter
            lambda                             //invokes lambda expression
        }


    }

    private fun observeCallListing() {
        lifecycleScope.launch {
            callListingViewModel.getCallLisitngStateFlow.collect { state ->
                handleStateChange(state)
            }


        }
    }
    private fun setViews(){
        recyclerView = binding.recylerview
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
    private fun setAdapter(){
        callListingAdapter = CallListingAdapter(list_of_data)
        recyclerView.adapter = callListingAdapter

    }
    private fun notifyRecyclerView(){
        recyclerView.adapter?.notifyDataSetChanged()
    }


    private fun handleStateChange(state:  CallListingViewModel.CallListingState) {
        when (state) {
            is CallListingViewModel.CallListingState.init -> Unit
            is CallListingViewModel.CallListingState.error -> handleError(state.rawResponse)
            is CallListingViewModel.CallListingState.success -> handleSuccess(state.listOfCallListing!!)
            is CallListingViewModel.CallListingState.message -> handleMessage(state.message)
            is CallListingViewModel.CallListingState.isLoading -> handleLoader(state.isLoading)
        }
    }

    private fun handleSuccess(listOfCallListing: ArrayList<CallListingModel>) {

        list_of_data = listOfCallListing
//        notifyRecyclerView()
        setAdapter()
    }

    private fun handleLoader(loading: Boolean) {

    }

    private fun handleMessage(message: String) {

    }



    private fun handleError(rawResponse: Any) {

    }
}