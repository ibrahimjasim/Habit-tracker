package com.example.habit_trawcker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val authRepo = AuthRepository()

    private val _authResult = MutableLiveData<Result<FirebaseUser>>()
    val authResult: LiveData<Result<FirebaseUser>> = _authResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = authRepo.login(email, password)
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = authRepo.register(email, password)
        }
    }

    fun getCurrentUser(): FirebaseUser? = authRepo.getCurrentUser()

    fun signOut() = authRepo.signOut()
}
