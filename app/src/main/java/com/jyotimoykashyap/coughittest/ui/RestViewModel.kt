package com.jyotimoykashyap.coughittest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jyotimoykashyap.coughittest.model.TodoResponse
import com.jyotimoykashyap.coughittest.respository.RestRepository
import com.jyotimoykashyap.coughittest.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class RestViewModel(
    val restRepository : RestRepository
) : ViewModel() {

    val todos : MutableLiveData<Resource<TodoResponse>> = MutableLiveData()

    fun getTodos() = viewModelScope.launch {
        todos.postValue(Resource.Loading())

        // we make the network call here
        val response = restRepository.getTodo()
        todos.postValue(handleRestResponse(response)) 
    }

    private fun handleRestResponse(response: Response<TodoResponse>) : Resource<TodoResponse>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }
}