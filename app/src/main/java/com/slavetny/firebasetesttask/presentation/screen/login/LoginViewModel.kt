package com.slavetny.firebasetesttask.presentation.screen.login

import com.google.firebase.auth.FirebaseAuth
import com.slavetny.firebasetesttask.presentation.base.BaseViewModel

class LoginViewModel : BaseViewModel() {
    fun login(email: String, password: String) =
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
}