package com.qfc.applicationmvvm.addbook.viewmodel

import android.app.Application
import androidx.lifecycle.*

import com.qfc.applicationmvvm.addbook.db.Book
import com.qfc.applicationmvvm.addbook.db.BookDatabase

import com.qfc.applicationmvvm.addbook.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class BookViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val allBooks : LiveData<List<Book>> = repository.allBook



    fun insert(book: Book) {
        viewModelScope.launch {
            repository.insert(book)
        }
    }

    fun update(book: Book) {
        viewModelScope.launch {
            repository.update(book)
        }
    }

    fun delete(book: Book) {
        viewModelScope.launch {
            repository.delete(book)
        }
    }



    fun getAllBook(): LiveData<List<Book>> {
        return allBooks
    }
}