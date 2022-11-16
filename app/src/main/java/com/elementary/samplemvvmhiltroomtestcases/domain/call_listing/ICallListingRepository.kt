package com.elementary.samplemvvmhiltroomtestcases.domain.call_listing

import androidx.annotation.WorkerThread
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.datasource.local.dao.CallListingDao
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.models.CallListingModel
import com.example.v_permotios_app.data.response.BaseResult
import kotlinx.coroutines.flow.Flow

interface ICallListingRepository {

    suspend fun getCallListing(): Flow<BaseResult<ArrayList<CallListingModel>, Any>>

    suspend fun getCallListingFromDatabase(): Flow<List<CallListingModel>>

    suspend fun insertCallListingInDatabase(callListingModel: CallListingModel)

}