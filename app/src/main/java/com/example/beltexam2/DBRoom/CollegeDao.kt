package com.example.beltexam2.DBRoom

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CollegeDao {

    //get me all the rows and arrangement by the title ASC
    @Query("SELECT * FROM Uni ")
    fun getAllCollege(): LiveData<List<CollegeEntity>>

    @Insert
    fun insertCollege(user: CollegeEntity)

    @Update
    fun updateCollege(user: CollegeEntity)

    @Delete
    fun deleteCollege(user: CollegeEntity)



}