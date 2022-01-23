package com.church.ministry.data.api

import com.church.ministry.util.NetworkHelper
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.peter.digicaradmin.data.api.ApiHelper
import com.peter.digicaradmin.data.api.ApiService
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val firebaseAuth: FirebaseAuth
) : ApiHelper {

    override suspend fun login(email: String, password: String): Task<AuthResult>? {
        if (networkHelper.isNetworkConnected())
            return firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {}
        return null
    }

    override suspend fun logout() {
        firebaseAuth.currentUser
    }


}