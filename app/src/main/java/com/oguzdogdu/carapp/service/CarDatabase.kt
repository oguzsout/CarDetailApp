package com.oguzdogdu.carapp.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oguzdogdu.carapp.model.ModelItem

@Database(entities = [ModelItem::class],version = 1)
abstract class CarDatabase : RoomDatabase() {
    abstract fun carDao() : CarDao

    //Singleton

    companion object {

        @Volatile private var instance : CarDatabase? = null

        private val lock = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }


        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,CarDatabase::class.java,"cardatabase"
        ).build()
    }
}