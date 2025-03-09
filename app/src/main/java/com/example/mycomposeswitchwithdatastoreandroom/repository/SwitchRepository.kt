package com.example.mycomposeswitchwithdatastoreandroom.repository
import com.example.mycomposeswitchwithdatastoreandroom.dao.SwitchDao
import com.example.mycomposeswitchwithdatastoreandroom.entity.SwitchEntity
import com.example.mycomposeswitchwithdatastoreandroom.helpers.DataStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SwitchRepository @Inject constructor(
    private val switchDao: SwitchDao,
    private val dataStoreManager: DataStoreManager
) {

    fun getSwitchStates(): Flow<List<SwitchEntity>> = switchDao.getAllSwitches()

    suspend fun updateSwitchState(id: Int, state: Boolean) {
        switchDao.updateSwitchState(id, state)
        dataStoreManager.saveSwitchState(id, state)
    }

    suspend fun getSwitchState(id: Int): Boolean {
        return dataStoreManager.getSwitchState(id).first()
    }



    suspend fun getStoredSwitchState(id: Int): Boolean {
        return dataStoreManager.getSwitchState(id).first()
    }




}