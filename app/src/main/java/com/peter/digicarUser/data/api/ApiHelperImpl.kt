package com.peter.digicarUser.data.api

import com.church.ministry.util.NetworkHelper
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.peter.digicarUser.data.cache.BookDao
import com.peter.digicarUser.data.model.ConsultationModel
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class ApiHelperImpl @Inject constructor(
    private val bookDao: BookDao,
    private val networkHelper: NetworkHelper,
    private val db: FirebaseFirestore
) : ApiHelper {


    override suspend fun addConsultation(consultation: String): Task<Void>? {
        return if (networkHelper.isNetworkConnected()) {

            val time = LocalDateTime.now().toString()
            val consultationMap = hashMapOf(
                "consultation" to consultation,
                "timeTxt" to time,
                "userName" to bookDao.getUser().userName
            )

            db.collection("consultation").document(time)
                .set(consultationMap)
                .addOnCompleteListener {
                    runBlocking {
                        bookDao.insertConsultation(ConsultationModel(0, time, consultation, false))
                    }
                }
        } else
            null
    }

    override suspend fun getAllConsultation(): List<ConsultationModel> {
        return bookDao.getAllConsultation()
    }

    override suspend fun logout() {

    }
}