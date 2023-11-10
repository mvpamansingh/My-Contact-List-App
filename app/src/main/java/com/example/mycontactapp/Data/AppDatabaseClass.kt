package com.example.mycontactapp.Data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ContactEntity::class], version = 1)
abstract class AppDatabaseClass: RoomDatabase() {
    abstract fun contactDao(): ContactEntityDao
}