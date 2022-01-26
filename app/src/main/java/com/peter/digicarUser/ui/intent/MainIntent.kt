package com.peter.digicarUser.ui.intent

sealed
class MainIntent {

    class Login(val email: String, val password: String) : MainIntent()
    class AddConsultation(val consultation: String) : MainIntent()
}