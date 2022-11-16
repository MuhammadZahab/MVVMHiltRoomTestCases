package com.elementary.samplemvvmhiltroomtestcases
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.repository.CallListingRepository
import com.elementary.samplemvvmhiltroomtestcases.domain.call_listing.CallListingUseCase
import com.elementary.samplemvvmhiltroomtestcases.ui.call_listing.CallListingActivity
import com.elementary.samplemvvmhiltroomtestcases.ui.call_listing.CallListingViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class CallListingViewModelTest {

    private lateinit var callListingViewModel:CallListingViewModel
    @get:Rule
    var activityTestRule = ActivityScenarioRule(CallListingActivity::class.java)
    @Inject
    lateinit var callListingRepository: CallListingRepository


    @Before
    public fun setUp(){

        var callListingUseCase = CallListingUseCase(callListingRepository)

        callListingViewModel = CallListingViewModel(callListingUseCase)


    }

    @Test
    fun callListingViewModel_Success_DataPopulated()  {

        var callListing  = callListingViewModel.getCallingListFromDatabase()
        var currentUiState:CallListingViewModel.CallListingState  = callListingViewModel.getCallLisitngStateFlow.value

        assertEquals(20,currentUiState)
    }

}