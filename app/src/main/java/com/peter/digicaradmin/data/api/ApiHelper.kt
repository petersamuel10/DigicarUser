package com.peter.digicaradmin.data.api

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult


interface ApiHelper {

    suspend fun login(email: String, password: String): Task<AuthResult>?
    suspend fun logout()
}