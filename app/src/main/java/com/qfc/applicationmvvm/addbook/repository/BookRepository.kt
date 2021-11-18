package com.qfc.applicationmvvm.addbook.repository


import androidx.lifecycle.LiveData
import com.qfc.applicationmvvm.addbook.db.Book
import com.qfc.applicationmvvm.addbook.db.BookDao
import javax.inject.Inject


class BookRepository @Inject constructor(private val bookDao: BookDao) {
    val allBook: LiveData<List<Book>> = bookDao.getAllBook()

    suspend fun insert(book: Book) = bookDao.addBook(book)

    suspend fun update(book: Book) = bookDao.updateBook(book)

    suspend fun delete(book: Book) = bookDao.deleteBook(book)

}