package com.qfc.applicationmvvm.randomlist.data.repositories

import com.qfc.applicationmvvm.randomlist.data.network.TodoApi

class TodoRepository(private val api: TodoApi): SafeApiRequest() {

    suspend fun getTodo() = apiRequest { api.getTodo() }
}