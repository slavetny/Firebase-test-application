package com.slavetny.firebasetesttask.presentation.screen.registation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.slavetny.firebasetesttask.R
import com.slavetny.firebasetesttask.domain.exception.CustomException
import com.slavetny.firebasetesttask.domain.extension.isEmailValid
import com.slavetny.firebasetesttask.domain.extension.observeNotNull
import kotlinx.android.synthetic.main.fragment_registration.*
import org.koin.android.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val viewModel: RegistrationViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorObserver()

        registrationButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            val confirmPassword = confirmPasswordField.text.toString()

            if (email.isEmailValid() && password.isNotEmpty() && password == confirmPassword) {
                viewModel.registration(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            findNavController().navigate(R.id.action_registrationFragment_to_notesFragment)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                task.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                viewModel.onHandleError(CustomException.LoginValidationException())
            }
        }
    }

    private fun errorObserver() {
        viewModel.errorLiveData.observeNotNull(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
}