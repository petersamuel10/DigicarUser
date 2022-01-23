package com.peter.digicaradmin.data.repository

import com.peter.digicaradmin.data.api.ApiHelper
import javax.inject.Inject

class MainRepository  @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun login(email: String, password: String) = apiHelper.login(email, password)
    suspend fun logout() = apiHelper.logout()
}