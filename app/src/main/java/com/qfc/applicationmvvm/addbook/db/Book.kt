package com.qfc.applicationmvvm.addbook.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Book (

        val name:String,
        val mobileNo:String,
        val bookName:String,
        val deleteFlag:Boolean

        ):Serializable{
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
}