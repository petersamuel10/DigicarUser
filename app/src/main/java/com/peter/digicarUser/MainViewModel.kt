package com.peter.digicarUser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peter.digicarUser.data.repository.MainRepository
import com.peter.digicarUser.ui.intent.MainIntent
import com.peter.digicarUser.ui.viewState.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    val mainIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val state: StateFlow<MainViewState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            mainIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.AddConsultation -> addConsultation(it.consultation)
                    is MainIntent.getAllConsultation -> getAllConsultation()
                }
            }
        }
    }

    private fun addConsultation(consultation: String) {

        viewModelScope.launch {
            _state.value = MainViewState.Loading
            val result = repository.addConsultation(consultation)
            if (result != null)
                _state.value = MainViewState.AddConsultation(result)
        }
    }

    private fun getAllConsultation() {

        viewModelScope.launch {
            _state.value = MainViewState.Loading
            _state.value = MainViewState.getAllConsultation(repository.getAllConsultation())
        }
    }


}
