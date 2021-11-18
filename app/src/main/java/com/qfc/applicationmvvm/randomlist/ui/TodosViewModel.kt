package com.qfc.applicationmvvm.randomlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qfc.applicationmvvm.randomlist.data.model.Todo
import com.qfc.applicationmvvm.randomlist.data.repositories.TodoRepository
import com.qfc.applicationmvvm.randomlist.util.Coroutines
import kotlinx.coroutines.Job

class TodosViewModel(private val repository: TodoRepository) : ViewModel() {

    private lateinit var job: Job
    private val _todos = MutableLiveData<List<Todo>>()
    val todos : LiveData<List<Todo>>
    get() = _todos

    fun getTodos(){

        job = Coroutines.ioTheMain(
            { repository.getTodo() },{
                _todos.value = it
            }
        )

    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized)job.cancel()
    }

}