package com.peter.digicaradmin.ui.viewState

sealed class MainViewState {

    object Idle : MainViewState()
    data class Error(val error: String?) : MainViewState()
}