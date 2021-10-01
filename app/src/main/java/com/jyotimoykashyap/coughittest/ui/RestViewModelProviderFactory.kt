package com.jyotimoykashyap.coughittest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jyotimoykashyap.coughittest.respository.RestRepository

class RestViewModelProviderFactory(val restRepository: RestRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RestViewModel(restRepository = restRepository) as T
    }
}