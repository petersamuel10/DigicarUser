package com.peter.digicaradmin.ui.intent

sealed
class MainIntent{

    class Login(val userName: String, val password: String) : MainIntent()
}