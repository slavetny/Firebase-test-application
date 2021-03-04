package com.slavetny.firebasetesttask.presentation.screen.registation

import com.google.firebase.auth.FirebaseAuth
import com.slavetny.firebasetesttask.presentation.base.BaseViewModel

class RegistrationViewModel : BaseViewModel() {
    fun registration(email: String, password: String) =
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
}