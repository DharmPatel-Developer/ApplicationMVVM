package com.qfc.applicationmvvm.addbook.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.qfc.applicationmvvm.R
import com.qfc.applicationmvvm.addbook.db.Book
import com.qfc.applicationmvvm.databinding.BookitemlayoutBinding

class BooksAdapter(private val books: List<Book>): RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {


        return BookViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.bookitemlayout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

     with(holder){
         binding.itemName.text = books[position].name
         binding.itemMobileNo.text = books[position].mobileNo
         binding.itemBookName.text = books[position].bookName

         binding.root.setOnClickListener {
             val action = HomeBookFragmentDirections.actionAddBook()
             action.book = books[position]
             Navigation.findNavController(it).navigate(action)
         }
     }
    }

    override fun getItemCount() = books.size

    class BookViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val binding = BookitemlayoutBinding.bind(view)

    }

}