package com.peter.digicarUser.ui.viewState

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentSnapshot
import com.peter.digicarUser.data.model.ConsultationModel

sealed class MainViewState {

    object Idle : MainViewState()
    object Loading : MainViewState()
    object Login : MainViewState()
    data class AddConsultation(val consultation: Task<Void>?) : MainViewState()
    data class getAllConsultation(val consultationList: List<ConsultationModel>) : MainViewState()
    data class Error(val error: String?) : MainViewState()
}