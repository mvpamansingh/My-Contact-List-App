package com.example.mycontactapp.ui

import com.example.mycontactapp.Data.ContactEntity

data class ContactState(

    val fullName:String= "",
    val position:String= "",
    val phoneNumber: String = "",
    val id:Int?= null,
    val listofContacts: List<ContactEntity> = emptyList(),
    val errorMessage:String?=null,
    val isAddingContact:Boolean = false

)
