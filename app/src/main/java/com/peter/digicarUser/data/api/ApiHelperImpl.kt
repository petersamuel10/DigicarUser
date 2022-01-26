package com.peter.digicarUser.data.api

import com.church.ministry.util.NetworkHelper
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService,
    private val networkHelper: NetworkHelper,
    private val db: FirebaseFirestore
) : ApiHelper {


    override suspend fun addConsultation(consultation: String): Task<Void>? {
        return if (networkHelper.isNetworkConnected()) {

            val consultationMap = hashMapOf("consultation" to consultation)
            db.collection("consultation").document(consultation)
                .set(consultationMap)
                .addOnCompleteListener {}
        } else
            null
    }


    override suspend fun logout() {

    }
}