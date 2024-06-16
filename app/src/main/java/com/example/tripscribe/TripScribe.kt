package com.example.tripscribe

import android.app.Application

class TripScribe: Application() {

    override fun onCreate() {
        super.onCreate()

        // initialize Amplify when application is starting
        AmplifyBackend.initialize(applicationContext)
    }
}