package com.elementary.samplemvvmhiltroomtestcases

import com.elementary.samplemvvmhiltroomtestcases.utils.Utility
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(Utility::class)
class ExampleUnitTest {
    @Test
    open fun TestStaticMethod_WithPowerMockito() {
        val call = " Hi there, I'm using PowerMock with Mockito "
        val callexpectation = " Call Expectation for you. "
        PowerMockito.mockStatic(Utility::class.java)
        PowerMockito.when(Utility.staticMethod(call)).thenReturn(callexpectation)
        val actualcall = Utility.staticMethod(call)
        assertEquals(callexpectation, actualcall)


    }

}