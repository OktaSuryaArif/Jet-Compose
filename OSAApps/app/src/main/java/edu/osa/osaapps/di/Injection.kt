package edu.osa.osaapps.di

import edu.osa.osaapps.data.TravelRepository

// Class For Injection
object Injection {
    fun provideRepository(): TravelRepository {
        return TravelRepository.getInstance()
    }
}