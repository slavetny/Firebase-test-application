package com.slavetny.firebasetesttask.di

import com.slavetny.firebasetesttask.data.NoteRepository
import com.slavetny.firebasetesttask.data.NoteRepositoryImpl
import com.slavetny.firebasetesttask.presentation.screen.addnote.AddNoteViewModel
import com.slavetny.firebasetesttask.presentation.screen.login.LoginViewModel
import com.slavetny.firebasetesttask.presentation.screen.notes.NotesViewModel
import com.slavetny.firebasetesttask.presentation.screen.registation.RegistrationViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel() }
    viewModel { RegistrationViewModel() }
    viewModel { NotesViewModel() }
    viewModel { AddNoteViewModel(get()) }

    single<NoteRepository> { NoteRepositoryImpl() }
}