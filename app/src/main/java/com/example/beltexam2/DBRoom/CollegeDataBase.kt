package com.example.beltexam2.DBRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [CollegeEntity::class],version = 1,exportSchema = false)
abstract class CollegeDataBase: RoomDatabase() {
    companion object{
        var instant: CollegeDataBase?=null
        fun getInstant(context: Context): CollegeDataBase {
            if(instant !=null)
            {
                return instant as CollegeDataBase
            }
            instant = Room.databaseBuilder(context, CollegeDataBase::class.java,"dbCollege").run{
                allowMainThreadQueries() }.build()
            return instant as CollegeDataBase
        }
    }

    abstract fun CollegeDao1():CollegeDao;
}