package com.example.mycontactapp.Dependency

import com.example.mycontactapp.Data.AppDatabaseClass
import com.example.mycontactapp.Data.ContactEntityDao
import com.example.mycontactapp.Data.ContactEntityDaoImpl
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppDependency {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabaseClass
    {
        return Room.databaseBuilder(
            appContext,
            AppDatabaseClass::class.java,
            "AppDatabaseClass"
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactDao(appDataBase : AppDatabaseClass) : ContactEntityDao {
        return appDataBase.contactDao()
    }

    @Provides
    @Singleton
    fun provideContactDaoImpl(dao: ContactEntityDao): ContactEntityDaoImpl
    {
        return ContactEntityDaoImpl(dao)
    }
}