package edu.osa.osaapps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.osa.osaapps.data.TravelRepository
import edu.osa.osaapps.ui.screen.detail.DetailTravelViewModel
import edu.osa.osaapps.ui.screen.home.HomeViewModel

// Class For ViewModelFactory
class ViewModelFactory(private val repository: TravelRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailTravelViewModel::class.java)) {
            return DetailTravelViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}