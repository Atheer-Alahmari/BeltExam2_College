package com.example.beltexam2.DBRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Uni") // name of the table of database
data class CollegeEntity (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")val id: Int = 0,
    @ColumnInfo(name = "uniName")val  uniName: String = "",
    @ColumnInfo(name = "country ")val country : String = "",
    @ColumnInfo(name = "note")val note: String = ""
)