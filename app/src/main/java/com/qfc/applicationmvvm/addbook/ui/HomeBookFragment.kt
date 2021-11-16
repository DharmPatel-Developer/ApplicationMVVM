package com.qfc.applicationmvvm.addbook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qfc.applicationmvvm.addbook.db.BookDatabase
import com.qfc.applicationmvvm.addbook.ui.HomeBookFragmentDirections.*
import com.qfc.applicationmvvm.databinding.FragmentHomeBookBinding
import kotlinx.coroutines.launch


class HomeBookFragment : BaseFragment() {

    private lateinit var binding:FragmentHomeBookBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBookBinding.inflate(inflater)
        return binding.root//inflater.inflate(R.layout.fragment_home_book, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewBook.setHasFixedSize(true)
        binding.viewBook.layoutManager = LinearLayoutManager(requireContext())


        launch {
            context?.let {
                val books = BookDatabase(it).getBookDao().getAllBook()
                binding.viewBook.adapter = BooksAdapter(books)


            }
        }


        binding.addBookButton.setOnClickListener {
            println("Clicked")

            val action = HomeBookFragmentDirections.actionAddBook()
            Navigation.findNavController(it).navigate(action)
        }

    }


}