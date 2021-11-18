package com.qfc.applicationmvvm.di

import android.content.Context
import androidx.room.Room
import com.qfc.applicationmvvm.Contants.Constants.Companion.BOOK_DATABASE
import com.qfc.applicationmvvm.addbook.db.BookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent ::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBookDatabase(
        @ApplicationContext app: Context)
    = Room.databaseBuilder(
            app,
            BookDatabase::class.java,
            BOOK_DATABASE
        ).build()

    @Singleton
    @Provides
    fun provideBookDao(db:BookDatabase) = db.getBookDao()

}