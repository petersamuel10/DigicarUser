package com.peter.digicarUser.ui.viewState

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentSnapshot

sealed class MainViewState {

    object Idle : MainViewState()
    object Loading : MainViewState()
    object Login : MainViewState()
    data class AddConsultation(val user: Task<Void>?) : MainViewState()
    data class Error(val error: String?) : MainViewState()
}