package com.qfc.applicationmvvm.randomlist.data.network

import com.qfc.applicationmvvm.randomlist.data.model.Todo
import retrofit2.Response
import retrofit2.Retrofit
import com.qfc.applicationmvvm.Contants.Constants.Companion.TODO_URL
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TodoApi {


    @GET(value = "todos")
    suspend fun getTodo(): Response<List<Todo>>

    companion object{

        private const val URL = "https://jsonplaceholder.typicode.com/"
        operator fun invoke(): TodoApi {
           return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(TODO_URL)
                .build()
                .create(TodoApi::class.java)

        }
    }
}