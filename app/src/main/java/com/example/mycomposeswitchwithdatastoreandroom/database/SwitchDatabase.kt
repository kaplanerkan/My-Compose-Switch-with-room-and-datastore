package com.example.mycomposeswitchwithdatastoreandroom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycomposeswitchwithdatastoreandroom.dao.SwitchDao
import com.example.mycomposeswitchwithdatastoreandroom.entity.SwitchEntity


@Database(entities = [SwitchEntity::class], version = 2, exportSchema = false)
abstract class SwitchDatabase : RoomDatabase() {
    abstract fun switchDao(): SwitchDao
}