package com.qfc.applicationmvvm.addbook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.qfc.applicationmvvm.addbook.viewmodel.BookViewModel
import com.qfc.applicationmvvm.databinding.FragmentHomeBookBinding
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeBookFragment : BaseFragment() {

    private lateinit var binding:FragmentHomeBookBinding
    private val viewModel: BookViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBookBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewBook.setHasFixedSize(true)
        binding.viewBook.layoutManager = LinearLayoutManager(requireContext())


        viewModel.getAllBook().observe(viewLifecycleOwner,
            { bookList -> binding.viewBook.adapter = BooksAdapter(bookList) })

        binding.addBookButton.setOnClickListener {
            println("Clicked")

            val action = HomeBookFragmentDirections.actionAddBook()
            Navigation.findNavController(it).navigate(action)
        }

    }


}