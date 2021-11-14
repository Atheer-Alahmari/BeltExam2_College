package com.example.beltexam2.View_Model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.beltexam2.DBRoom.CollegeDataBase
import com.example.beltexam2.DBRoom.CollegeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel (application: Application): AndroidViewModel(application) {

    private var userInfo: LiveData<List<CollegeEntity>>
    private val databaseObject: CollegeDataBase


    init {
        databaseObject = CollegeDataBase.getInstant(application)
        userInfo = databaseObject.CollegeDao1().getAllCollege()
    }

    fun getAllCollegeInfo(): LiveData<List<CollegeEntity>> {
        return userInfo
    }

    fun addCollege( uniName: String, country: String, note: String) {
        CoroutineScope(Dispatchers.IO).launch {
            databaseObject.CollegeDao1().insertCollege(CollegeEntity(0, uniName, country, note))
        }
    }
    fun updateNote(id: Int,name:String ,country:String ,note: String) {
        CoroutineScope(Dispatchers.IO).launch {
            databaseObject.CollegeDao1().updateCollege(CollegeEntity(id,name,country,note))
        }
    }

    fun deleteCollege(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            databaseObject.CollegeDao1().deleteCollege(CollegeEntity(id))
        }
    }
}