package com.qfc.applicationmvvm.addbook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.qfc.applicationmvvm.Contants.Constants.Companion.BOOK_DATABASE


@Database(entities = arrayOf(Book::class), version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {

    abstract fun getBookDao(): BookDao

    companion object {
        // Singleton prevents multiple
        // instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    BOOK_DATABASE
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}