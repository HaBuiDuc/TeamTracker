package com.buiducha.teamtracker

import android.app.Application
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.repository.StreamRepository

class TeamTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseRepository.initialize(this)
        StreamRepository.initialize(this)
    }
}