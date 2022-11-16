package com.elementary.samplemvvmhiltroomtestcases.di

import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.datasource.local.dao.CallListingDao
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.datasource.remote.CallListingAPI
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.repository.CallListingRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class CallListingModule {


    @Singleton
    @Provides
    fun provideCallListingAPI(retrofit: Retrofit): CallListingAPI {
        return retrofit.create(CallListingAPI::class.java)
    }


    @Singleton
    @Provides
    fun provideCallListingRepository(callListingAPI: CallListingAPI,callListingDao: CallListingDao): CallListingRepository {
        return CallListingRepository(callListingAPI,callListingDao)
    }


}