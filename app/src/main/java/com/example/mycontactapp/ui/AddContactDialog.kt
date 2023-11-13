package com.example.mycontactapp.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactDialog(
    state: ContactState,
    event: (ContactScreenEvent)->Unit
)
{


    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = { event(ContactScreenEvent.hideDialog) },
        confirmButton = { 
            //event(ContactScreenEvent.save)     //  we don't have to directly specify events handlers , create  a button  like below and do it
             Button(onClick = {

                 event(ContactScreenEvent.save)
                 if (state.fullName.isBlank() || state.position.isBlank() || state.phoneNumber.isBlank()) {
                     Toast.makeText(context, "Fields can't be empty", Toast.LENGTH_SHORT).show()
                 }


             }) {
                 Text(text = "Save")
             }
                        },

        title = { Text(text = "Add Contact")},
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                val context= LocalContext.current
                OutlinedTextField(value = state.fullName, onValueChange = {event(ContactScreenEvent.fullNameChanged(it))}
                    , label = { Text(text = "Full Name") }, )
                OutlinedTextField(value = state.position, onValueChange = {event(ContactScreenEvent.positionChanged(it))}
                    , label = { Text(text = "Position") } )
                OutlinedTextField(value = state.phoneNumber, onValueChange = {event(ContactScreenEvent.phonenumberChanged(it))}
                    , label = { Text(text = "Phone number") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

            //  In text ={} we don't have to specify any other buttons the app will not function accordingly

//                Row (modifier= Modifier.fillMaxWidth())
//                {
//                    Button(onClick = { event(ContactScreenEvent.save)
//                        if (state.fullName.isBlank() || state.position.isBlank() || state.phoneNumber.isBlank()) {
//                            Toast.makeText(context, "Fields can't be empty", Toast.LENGTH_SHORT).show()
//                        }
//                    }) {
//                        Text(text = "Save")
//                    }
//                    Spacer(modifier= Modifier.width(20.dp) )
//                    Button(onClick = { event(ContactScreenEvent.clearAll) }) {
//                        Text("Clear All")
//                    }
//                }
            }
        }
    )
}
