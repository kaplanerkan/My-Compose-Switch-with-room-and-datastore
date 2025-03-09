package com.example.mycomposeswitchwithdatastoreandroom.dao

import androidx.room.*
import com.example.mycomposeswitchwithdatastoreandroom.entity.SwitchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SwitchDao {

    @Query("SELECT * FROM switchentity")
    fun getAllSwitches(): Flow<List<SwitchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSwitch(switchEntity: SwitchEntity)

    @Query("UPDATE switchentity SET state = :state WHERE id = :id")
    suspend fun updateSwitchState(id: Int, state: Boolean)
}