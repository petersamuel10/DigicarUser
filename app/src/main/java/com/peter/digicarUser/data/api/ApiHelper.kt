package com.peter.digicarUser.data.api

import com.google.android.gms.tasks.Task
import com.peter.digicarUser.data.model.Temp


interface ApiHelper {

    suspend fun addConsultation(consultation: String): Task<Void>?
    suspend fun logout()
}