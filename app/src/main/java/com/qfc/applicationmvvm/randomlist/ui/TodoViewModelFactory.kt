package com.qfc.applicationmvvm.randomlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qfc.applicationmvvm.randomlist.data.repositories.TodoRepository

class TodoViewModelFactory(
    private val repository: TodoRepository
    ): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TodosViewModel(repository) as T
        }
}