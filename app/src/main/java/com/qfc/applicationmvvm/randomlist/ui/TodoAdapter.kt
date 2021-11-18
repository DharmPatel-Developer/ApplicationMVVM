package com.qfc.applicationmvvm.randomlist.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.qfc.applicationmvvm.R
import com.qfc.applicationmvvm.databinding.TodoItemLayoutBinding
import com.qfc.applicationmvvm.randomlist.data.model.Todo

class TodoAdapter (private val todo: List<Todo>):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){


    inner class TodoViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val binding = TodoItemLayoutBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {


       return TodoViewHolder(
           LayoutInflater.from(parent.context)
               .inflate(R.layout.todo_item_layout, parent, false)
       )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {

        holder.binding.itemId.text = "ID: ${todo[position].id}"
        holder.binding.itemTitle.text = "Title: ${todo[position].title}"
        holder.binding.itemUserId.text = "UserId: ${todo[position].userId}"
        holder.binding.itemStatus.text = "Completed: ${todo[position].completed}"

    }

    override fun getItemCount() = todo.size
}