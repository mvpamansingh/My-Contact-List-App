package com.example.mycontactapp.Data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactEntityDaoImpl @Inject constructor(
    private val dao: ContactEntityDao
){

    fun getAllContactI(): Flow<List<ContactEntity>>
    {
        return dao.getAllContactEntity()
    }

    suspend  fun deleteContactI(contactEntity: ContactEntity)
    {
        dao.deleteContact(contactEntity)
    }

    suspend fun insertContactI(contactEntity: ContactEntity)
    {
        dao.insertContact(contactEntity)
    }

    suspend  fun clearTable()
    {
        dao.deleteAllContactEntity()
    }
}