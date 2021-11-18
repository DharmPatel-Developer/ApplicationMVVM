package com.qfc.applicationmvvm.randomlist.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.qfc.applicationmvvm.databinding.TodosFragmentBinding
import com.qfc.applicationmvvm.randomlist.data.network.TodoApi
import com.qfc.applicationmvvm.randomlist.data.repositories.TodoRepository

class TodosFragment : Fragment() {

    private lateinit var factory: TodoViewModelFactory
    private lateinit var viewModel: TodosViewModel
    private lateinit var binding: TodosFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TodosFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = TodoApi()
        val repository = TodoRepository(api)
        factory = TodoViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory)[TodosViewModel::class.java]
        binding.progressbar.visibility = View.VISIBLE


        viewModel.getTodos()
        viewModel.todos.observe(viewLifecycleOwner,
            Observer {todo ->
                binding.todoList.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    val todoAdapter = TodoAdapter(todo)
                    println("ITEM:${todoAdapter.itemCount}")
                    it.adapter = todoAdapter
                    binding.progressbar.visibility = View.GONE

                }
            }
        )
    }

}