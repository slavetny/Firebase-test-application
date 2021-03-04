package com.slavetny.firebasetesttask

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.slavetny.firebasetesttask.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TestTaskApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestTaskApplication)
            androidLogger()
            modules(
                listOf(
                    viewModelModule
                )
            )
        }
    }
}