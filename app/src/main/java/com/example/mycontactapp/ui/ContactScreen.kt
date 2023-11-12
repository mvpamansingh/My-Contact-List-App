package com.example.mycontactapp.ui

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mycontactapp.Data.ContactEntity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(state: ContactState,
                  event:(ContactScreenEvent)->Unit)
{
    Column(modifier= Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
            val context= LocalContext.current

        Column(modifier= Modifier.fillMaxWidth()) {
            OutlinedTextField(value = state.fullName, onValueChange = {event(ContactScreenEvent.fullNameChanged(it))}
            , label = { Text(text = "Full Name") }, )
            OutlinedTextField(value = state.position, onValueChange = {event(ContactScreenEvent.positionChanged(it))}
                , label = { Text(text = "Position") } )
            OutlinedTextField(value = state.phoneNumber, onValueChange = {event(ContactScreenEvent.phonenumberChanged(it))}
                , label = { Text(text = "Phone number") } )


            Row (modifier= Modifier.fillMaxWidth()){
                Button(onClick = { event(ContactScreenEvent.save)
                    if (state.fullName.isBlank() || state.position.isBlank() || state.phoneNumber.isBlank()) {
                        Toast.makeText(context, "Fields can't be empty", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text(text = "Save")
                }
                Spacer(modifier=Modifier.width(20.dp) )
                Button(onClick = { event(ContactScreenEvent.clearAll) }) {
                    Text("Clear All")
                }
            }
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp)){

            items(state.listofContacts)
            {contactList->
                ContactCard(contactList=contactList, event)

            }
        }


    }


}







@Composable
fun ContactCard(contactList:ContactEntity,
                event: (ContactScreenEvent)->Unit)
{
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = contactList.fullName,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start)
            Text(text = contactList.position,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start)
            Text(text = contactList.phoneNumber,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start)


            Button(onClick = { event(ContactScreenEvent.deleteContact(contactList)) },
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Delete")
            }


        }
    }
}