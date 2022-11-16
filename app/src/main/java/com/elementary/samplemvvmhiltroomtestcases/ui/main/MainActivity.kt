package com.elementary.samplemvvmhiltroomtestcases.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.elementary.samplemvvmhiltroomtestcases.R
import com.elementary.samplemvvmhiltroomtestcases.databinding.ActivityMainBinding
import com.elementary.samplemvvmhiltroomtestcases.ui.call_listing.CallListingActivity

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setListeners()


    }
    private fun setListeners(){

        binding.btnFirst.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var intent = Intent(this,CallListingActivity::class.java)
        startActivity(intent)

    }
}