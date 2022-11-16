package com.elementary.samplemvvmhiltroomtestcases.data.call_listing.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.datasource.local.dao.CallListingDao
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.datasource.remote.CallListingAPI
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.models.CallListingModel
import com.elementary.samplemvvmhiltroomtestcases.domain.call_listing.ICallListingRepository
import com.example.v_permotios_app.data.response.BaseResult
import kotlinx.coroutines.flow.*
import retrofit2.Retrofit
import javax.inject.Inject

class CallListingRepository @Inject constructor(private val callListingAPI:CallListingAPI,private val callListingDao: CallListingDao):
    ICallListingRepository {

    lateinit  var retrofit:Retrofit

    var callListingAPI2:CallListingAPI = retrofit.create(CallListingAPI::class.java)


    override suspend fun getCallListing(): Flow<BaseResult<ArrayList<CallListingModel>, Any>> {
        return flow {
            val response = callListingAPI.getCallListing()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    val body = response.body()
                    val list = body
                    emit(BaseResult.Success(list))
                    Log.d("TAG", "callListing: $list")
                } else {
                    emit(BaseResult.Error("Error"))
                }
            }
        }
    }

    @WorkerThread
    override suspend fun getCallListingFromDatabase(): Flow<List<CallListingModel>> {

        return flow{

            callListingDao.getCallListingFromDatabse().collect{
               emit(it)
               }
           }

           }


    @WorkerThread
    override suspend fun insertCallListingInDatabase(callListingModel: CallListingModel) {
        callListingDao.insert(callListingModel)
    }
}