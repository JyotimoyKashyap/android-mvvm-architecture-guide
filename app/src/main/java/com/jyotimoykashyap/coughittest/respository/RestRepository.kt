package com.jyotimoykashyap.coughittest.respository

import com.jyotimoykashyap.coughittest.api.RetrofitInstance

class RestRepository {
    suspend fun getTodo() =
        RetrofitInstance.api.getTodos()
}