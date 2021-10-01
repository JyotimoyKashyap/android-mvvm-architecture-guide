package com.jyotimoykashyap.coughittest.api

import com.jyotimoykashyap.coughittest.model.TodoResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestApi {

    @GET("/todos")
    suspend fun getTodos() : Response<TodoResponse>
}