package com.elementary.samplemvvmhiltroomtestcases.data.call_listing.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elementary.samplemvvmhiltroomtestcases.data.call_listing.models.CallListingModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CallListingDao {

    @Query("SELECT * FROM call_listing_table")  //Fetching all data from room
    fun getCallListingFromDatabse(): Flow<List<CallListingModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)  // onConflict strategy ignores a new word if it's exactly the same as one already in the list
    suspend fun insertCallListingList(listOfCallListing: List<CallListingModel>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(callListingModel: CallListingModel)

    @Query("DELETE FROM call_listing_table")
       suspend fun deleteAll()
}