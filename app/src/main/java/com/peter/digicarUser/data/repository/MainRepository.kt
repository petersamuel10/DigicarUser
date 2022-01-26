package com.peter.digicarUser.data.repository

import com.peter.digicarUser.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun addConsultation(txt: String) = apiHelper.addConsultation(txt)
    suspend fun getAllConsultation() = apiHelper.getAllConsultation()
}