package com.example.mycomposeswitchwithdatastoreandroom.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity

data class SwitchEntity(
    @PrimaryKey val id: Int,

    val ayarAdi: String,

    val description: String,

    val state: Boolean,

)
