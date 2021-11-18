package com.qfc.applicationmvvm.addbook.db

import androidx.room.*
import androidx.lifecycle.LiveData



@Dao
interface BookDao {


    @Insert
    suspend fun addBook(book: Book)

    @Query(value = "SELECT * FROM book ORDER BY id DESC")
    fun getAllBook(): LiveData<List<Book>>

    @Insert
    suspend fun addMultipleBooks(vararg book: Book)

    @Update
    suspend fun updateBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

}