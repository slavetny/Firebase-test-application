package com.slavetny.firebasetesttask.presentation.screen.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.slavetny.firebasetesttask.R
import com.slavetny.firebasetesttask.domain.exception.CustomException
import com.slavetny.firebasetesttask.domain.extension.isEmailValid
import com.slavetny.firebasetesttask.domain.extension.observeNotNull
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorObserver()

        if (FirebaseAuth.getInstance().currentUser != null) {
            findNavController().navigate(R.id.action_loginFragment_to_notesFragment)
        }

        loginButton.setOnClickListener {
            if (emailField.text.toString().isEmailValid() && passwordField.text.toString().isNotEmpty()) {
                viewModel.login(emailField.text.toString(), passwordField.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            findNavController().navigate(R.id.action_loginFragment_to_notesFragment)
                        } else {
                            Toast.makeText(requireContext(), task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                viewModel.onHandleError(CustomException.LoginValidationException())
            }
        }

        registrationButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

    private fun errorObserver() {
        viewModel.errorLiveData.observeNotNull(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}