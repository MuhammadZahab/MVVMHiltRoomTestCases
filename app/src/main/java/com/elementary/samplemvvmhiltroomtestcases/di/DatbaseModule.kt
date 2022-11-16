package com.elementary.samplemvvmhiltroomtestcases.di

import android.content.Context
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.datasource.local.dao.CallListingDao
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.repository.CallListingRepository
import com.elementary.samplemvvmhiltroomtestcases.data.roomdb.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatbaseModule {


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
        @Singleton
        @Provides
        fun provideStudentDao(appDatabase: AppDatabase) : CallListingDao {
            return appDatabase.callListingDao()
        }
}