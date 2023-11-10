package com.example.mycontactapp.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactEntityDao {

    @Insert
    suspend fun insertContact(contactEntity: ContactEntity)




    @Delete
   suspend fun deleteContact(contactEntity: ContactEntity)

    @Query("select * from ContactEntity order by fullName")
    fun getAllContactEntity(): Flow<List<ContactEntity>>

    @Query("delete from ContactEntity")
    suspend fun deleteAllContactEntity()
}