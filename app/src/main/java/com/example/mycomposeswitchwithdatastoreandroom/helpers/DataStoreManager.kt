package com.example.mycomposeswitchwithdatastoreandroom.helpers


import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "settings")

class DataStoreManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val SWITCH_KEY_PREFIX = "switch_"

    suspend fun saveSwitchState(id: Int, state: Boolean) {
        context.dataStore.edit { settings ->
            settings[booleanPreferencesKey(SWITCH_KEY_PREFIX + id)] = state
        }
    }

    fun getSwitchState(id: Int): Flow<Boolean> {
        return context.dataStore.data.map { settings ->
            settings[booleanPreferencesKey(SWITCH_KEY_PREFIX + id)] ?: false
        }
    }


    suspend fun saveIntState(id: Int, state: Boolean) {
        context.dataStore.edit { settings ->
            settings[booleanPreferencesKey(SWITCH_KEY_PREFIX + id)] = state
        }
    }

}