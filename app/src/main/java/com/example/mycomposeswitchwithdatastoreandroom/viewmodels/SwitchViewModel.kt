package com.example.mycomposeswitchwithdatastoreandroom.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycomposeswitchwithdatastoreandroom.repository.SwitchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SwitchViewModel @Inject constructor(
    private val repository: SwitchRepository
) : ViewModel() {

    val switchStates: StateFlow<Triple<Boolean, Boolean, Boolean>> = repository.getSwitchStates()
        .map { list ->
            Triple(list[0].state, list[1].state, list[2].state)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, Triple(false, false, false))

    fun onSwitchChanged(id: Int, state: Boolean) {
        viewModelScope.launch {
            repository.updateSwitchState(id, state)
        }
    }


    /**
     * Datenbank uzerinden kayitli olan statuslari okur
     */
    suspend fun getSwitchState(id: Int): Boolean {
        return repository.getSwitchState(id)
    }


    /*
    * DataStorageManager Uzerinden kayit yapilan statuslari olur
    * */
    suspend fun getStoredSwitchState(id: Int): Boolean {
        return repository.getStoredSwitchState(id)
    }

}