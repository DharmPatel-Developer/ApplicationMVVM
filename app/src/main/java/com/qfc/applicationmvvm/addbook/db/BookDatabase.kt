package com.qfc.applicationmvvm.addbook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Book::class],
    version = 1
)
abstract class BookDatabase : RoomDatabase() {

    abstract fun getBookDao(): BookDao

    companion object {

       @Volatile private var instance: BookDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context). also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            BookDatabase::class.java,
            "bookDatabase"
        ).build()
    }

}