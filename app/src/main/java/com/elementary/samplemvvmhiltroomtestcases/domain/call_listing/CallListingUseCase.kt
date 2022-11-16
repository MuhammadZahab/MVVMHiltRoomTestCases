package com.elementary.samplemvvmhiltroomtestcases.domain.call_listing

import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.models.CallListingModel
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.repository.CallListingRepository
import com.example.v_permotios_app.data.response.BaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CallListingUseCase @Inject constructor(val callListinRepository:CallListingRepository) {
    suspend fun getCallListing(): Flow<BaseResult<ArrayList<CallListingModel>, Any>> {
        return callListinRepository.getCallListing();
    }

    suspend fun getCallListingFromDatabase(): Flow<List<CallListingModel>> {
        return callListinRepository.getCallListingFromDatabase()
    }
    suspend fun insertCallListingInDatabase(callListingModel: CallListingModel) {
        return callListinRepository.insertCallListingInDatabase(callListingModel);
    }


}