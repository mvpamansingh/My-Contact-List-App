package com.example.mycontactapp.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    val fullName:String,
    val position:String,
    val phoneNumber:String
)
