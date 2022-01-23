package com.peter.digicaradmin.ui.viewState

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

sealed class MainViewState {

    object Idle : MainViewState()
    object Loading : MainViewState()

    data class Login(val user: Task<AuthResult>?) : MainViewState()
    class Logout(logout: Unit) : MainViewState()
    data class Error(val error: String?) : MainViewState()
}