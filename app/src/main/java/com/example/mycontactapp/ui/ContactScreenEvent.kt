package com.example.mycontactapp.ui

import com.example.mycontactapp.Data.ContactEntity

sealed interface ContactScreenEvent
{

    data class fullNameChanged(val v:String): ContactScreenEvent
    data class phonenumberChanged(val v :String): ContactScreenEvent
    data class positionChanged(val v:String): ContactScreenEvent
    object save: ContactScreenEvent

    object clearAll: ContactScreenEvent
    data class deleteContact(val v : ContactEntity): ContactScreenEvent
}