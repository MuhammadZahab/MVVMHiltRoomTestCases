package com.elementary.samplemvvmhiltroomtestcases.ui.call_listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.models.CallListingModel
import com.elementary.samplemvvmhiltroomtestcases.domain.call_listing.CallListingUseCase
import com.example.v_permotios_app.data.response.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallListingViewModel @Inject constructor(private val callListingUseCase: CallListingUseCase):ViewModel() {
    private val callListingStateFlow = MutableStateFlow<CallListingState>(CallListingState.init)
    val getCallLisitngStateFlow: StateFlow<CallListingState> = callListingStateFlow.asStateFlow()


    fun setLoading(isLoading: Boolean){
        callListingStateFlow.value = CallListingState.isLoading(isLoading)
    }

    fun setMessage(message:String){
          callListingStateFlow.value = CallListingState.message(message)
    }

   fun getCallingList() {

      viewModelScope.launch {
          callListingUseCase.getCallListing().onStart {
              setLoading(true)
          }.catch {excpetion->
              setLoading(false)
              setMessage(excpetion.message.toString())
          }
              .collect() {
                      baseResult->
                  when (baseResult) {

                      is BaseResult.Success -> {

                              for (index in baseResult.data!!){
                                insertCallListingDataInDatabase(index);
                              }

                      }


                      is BaseResult.Error -> callListingStateFlow.value =
                          CallListingState.error(baseResult.rawResponse)
                  }
              }
      }
  }

    fun insertCallListingDataInDatabase(callListingModel: CallListingModel){
        viewModelScope.launch {

            callListingUseCase.insertCallListingInDatabase(callListingModel)
        }
    }

     fun getCallingListFromDatabase() {

        viewModelScope.launch {
            callListingUseCase.getCallListingFromDatabase().onStart {
                setLoading(true)
            }.catch {excpetion->
                setLoading(false)
                setMessage(excpetion.message.toString())
            }
                .collect() {
                        list->
                        callListingStateFlow.value =
                            CallListingState.success(list as ArrayList<CallListingModel> /* = java.util.ArrayList<com.elementary.samplemvvmhiltroomtestcases.data.call_listing.models.CallListingModel> */)
                    }
                    }
                }



     sealed class CallListingState {
        object init : CallListingState()
        data class isLoading(val isLoading: Boolean) : CallListingState()
        data class message(val message: String) : CallListingState()
        data class success(val listOfCallListing: ArrayList<CallListingModel>?) : CallListingState()
        data class error(val rawResponse: Any) : CallListingState()
    }
}

