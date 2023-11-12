package com.example.mycontactapp.ui

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.mycontactapp.Data.ContactEntityDaoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycontactapp.Data.ContactEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class ContactScreenViewModel @Inject constructor(
    private val dao:ContactEntityDaoImpl
) :ViewModel(){


    init {
        viewModelScope.launch(Dispatchers.IO)
        {
            delay(300)
            dao.getAllContactI().collect{list->
                _state.update {
                    it.copy(listofContacts = list)
                }
            }
        }
    }





    private val _state= MutableStateFlow(ContactState())
    val state= _state.asStateFlow()

    fun events(e:ContactScreenEvent)
    {
        when(e)
        {
            ContactScreenEvent.clearAll ->
            {
                viewModelScope.launch(Dispatchers.IO)
                {

                    dao.clearTable()
                }
            }
            is ContactScreenEvent.deleteContact ->
            {
                viewModelScope.launch(Dispatchers.IO)
                {
                    dao.deleteContactI(e.v)
                }
            }
            is ContactScreenEvent.fullNameChanged ->
            {

                _state.update {
                    it.copy(
                        fullName = e.v
                    )
                }
            }
            is ContactScreenEvent.phonenumberChanged ->
            {
                _state.update {
                    it.copy(
                        phoneNumber = e.v
                    )
                }
            }
            is ContactScreenEvent.positionChanged ->
            {
                _state.update {
                    it.copy(
                        position = e.v
                    )
                }
            }
            ContactScreenEvent.save ->
            {

                viewModelScope.launch(Dispatchers.IO)
                {
                    if(state.value.fullName.isBlank() || state.value.position.isBlank() || state.value.phoneNumber.isBlank())
                    {
                        return@launch
                    }
                    if(state.value.id == null){
                        dao.insertContactI(contactEntity =
                        ContactEntity(fullName = state.value.fullName,
                            phoneNumber = state.value.phoneNumber,
                            position = state.value.position,
                            id = state.value.id)
                        )


                        _state.update {
                            it.copy(
                                fullName = "",
                                position = "",
                                phoneNumber = "",
                                errorMessage = null

                            )
                        }


                    }else{
                        TODO("Update the todo with the id")
                    }

                }
            }
        }
    }

}