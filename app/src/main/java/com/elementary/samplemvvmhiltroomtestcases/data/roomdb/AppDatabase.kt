package com.elementary.samplemvvmhiltroomtestcases.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.datasource.local.dao.CallListingDao
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.models.CallListingModel


@Database(entities = arrayOf(CallListingModel::class), version = 1, exportSchema = false)
    public abstract class AppDatabase : RoomDatabase() {

        abstract fun callListingDao(): CallListingDao

        companion object {
            // Singleton prevents multiple instances of database opening at the
            // same time.
            @Volatile
            private var INSTANCE: AppDatabase? = null

            fun getInstance(context: Context): AppDatabase {
                // if the INSTANCE is not null, then return it,
                // if it is, then create the database
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                    INSTANCE = instance
                    // return instance
                    instance
                }
            }
        }

}