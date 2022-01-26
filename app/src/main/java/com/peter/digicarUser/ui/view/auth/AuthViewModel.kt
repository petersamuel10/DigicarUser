package com.peter.digicarUser.ui.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.peter.digicarUser.data.cache.BookDao
import com.peter.digicarUser.data.model.User
import com.peter.digicarUser.data.repository.MainRepository
import com.peter.digicarUser.ui.intent.MainIntent
import com.peter.digicarUser.ui.viewState.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class AuthViewModel @Inject constructor(
    private val repository: MainRepository,
    val bookDao: BookDao,
    private val db: FirebaseFirestore
) : ViewModel() {

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val state: StateFlow<MainViewState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.Login -> login(it.email, it.password)
                }
            }
        }
    }

    private fun login(email: String, password: String) {
        _state.value = MainViewState.Loading
        viewModelScope.launch {
            val docRef = db.collection("users").document(email)
            docRef.get().addOnSuccessListener {
                if (it.data?.get("password") == password) {
                    runBlocking {
                        bookDao.insertUser(
                            User(
                                0,
                                it.data?.get("userName").toString(),
                                it.data?.get("phoneNumber").toString()
                            )
                        )
                    }
                    _state.value = MainViewState.Login
                } else
                    _state.value = MainViewState.Error("Account not register")
            }.addOnFailureListener {
                _state.value = MainViewState.Error(it.toString())
            }
        }
    }
}