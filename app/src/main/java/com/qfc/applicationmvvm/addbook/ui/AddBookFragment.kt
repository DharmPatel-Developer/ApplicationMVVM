package com.qfc.applicationmvvm.addbook.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.*
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.Navigation
import com.qfc.applicationmvvm.Contants.Constants
import com.qfc.applicationmvvm.addbook.db.Book
import com.qfc.applicationmvvm.addbook.db.BookDatabase
import com.qfc.applicationmvvm.databinding.FragmentAddBookBinding
import com.qfc.applicationmvvm.enable
import com.qfc.applicationmvvm.toast
import kotlinx.coroutines.launch
import com.qfc.applicationmvvm.R



class AddBookFragment : BaseFragment() {

    private lateinit var binding: FragmentAddBookBinding

    private var book:Book? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAddBookBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root//inflater.inflate(R.layout.fragment_add_book, container, false)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.delete -> if(book != null) deleteBook() else context?.toast("Cannot Delete Book")
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteBook(){
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You cannot undo this operation")
            setPositiveButton("Yes"){_, _ ->
                launch {
                    BookDatabase(requireActivity()).getBookDao().deleteBook(book!!)
                    val action = AddBookFragmentDirections.actionSaveBook()
                    Navigation.findNavController(binding.root).navigate(action)
                }
            }
            setNegativeButton("No"){_, _ ->

            }.create().show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            book = AddBookFragmentArgs.fromBundle(it).book

            if (book != null) {
                binding.name.text = SpannableStringBuilder(book?.name)
                binding.mobileNo.text = SpannableStringBuilder(book?.mobileNo)
                val bookList = resources.getStringArray(R.array.bookArray)
                binding.bookList.setSelection(bookList.indexOf(book?.bookName))
            }
        }

     /*   binding.mobileNo.addTextChangedListener {

            val name = binding.name.text.toString().trim()
            val bookName = binding.bookList.selectedItem.toString()

            binding.buttonSave.enable(name.isNotEmpty() && it.toString().isNotEmpty()
                    && it.toString().length == 10 && bookName != Constants.SELECT)


        }*/

        binding.buttonSave.setOnClickListener {view ->

            val name = binding.name.text.toString().trim()
            val mobileNo = binding.mobileNo.text.toString().trim()
            val bookName = binding.bookList.selectedItem.toString()

            if(name.isEmpty()){
                binding.name.error = "Name Required"
                binding.name.requestFocus()
                return@setOnClickListener
            }

            if(mobileNo.isEmpty()){
                binding.mobileNo.error = "Mobile No Required"
                binding.mobileNo.requestFocus()
                return@setOnClickListener
            }

            if(bookName == Constants.SELECT){
                binding.bookList.requestFocus()
                return@setOnClickListener
            }

            launch {
                val mbook = Book(name, mobileNo, bookName,false)
                context?.let {

                    if(book == null){
                        BookDatabase(requireActivity()).getBookDao().addBook(mbook)
                        it.toast("Book Saved")

                    }else{
                        mbook.id = book!!.id
                        BookDatabase(requireActivity()).getBookDao().updateBook(mbook)
                        it.toast("Book Updated")

                    }

                    val action = AddBookFragmentDirections.actionSaveBook()
                    Navigation.findNavController(view).navigate(action)
                }

            }
            //
        }
        //
    }

}