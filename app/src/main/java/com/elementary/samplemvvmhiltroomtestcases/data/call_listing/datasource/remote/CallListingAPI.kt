package com.elementary.samplemvvmhiltroomtestcases.data.call_listing.datasource.remote

import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.models.CallListingModel
import com.example.v_permotios_app.data.response.WrappedResponse
import retrofit2.Response
import retrofit2.http.GET

interface CallListingAPI {

   @GET("imkhan334/demo-1/call")
   suspend fun getCallListing(): Response<ArrayList<CallListingModel>>
}