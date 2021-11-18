package com.qfc.applicationmvvm.addbook.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.qfc.applicationmvvm.Contants.Constants
import com.qfc.applicationmvvm.addbook.db.Book
import com.qfc.applicationmvvm.databinding.FragmentAddBookBinding
import com.qfc.applicationmvvm.toast
import kotlinx.coroutines.launch
import com.qfc.applicationmvvm.R
import com.qfc.applicationmvvm.addbook.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBookFragment : BaseFragment() {

    private lateinit var binding: FragmentAddBookBinding
    private val bookViewModel: BookViewModel by viewModels()


    private var book:Book? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAddBookBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
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

                bookViewModel.delete(book!!)
                val action = AddBookFragmentDirections.actionSaveBook()
                Navigation.findNavController(binding.root).navigate(action)
                context.toast("Record Delete")
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


        binding.buttonSave.setOnClickListener {view ->

            val name = binding.name.text.toString().trim()
            val mobileNo = binding.mobileNo.text.toString().trim()
            val bookName = binding.bookList.selectedItem.toString()

            if(name.isEmpty()){
                binding.name.error = "Name Required"
                binding.name.requestFocus()
                return@setOnClickListener
            }

            if(mobileNo.isEmpty() || mobileNo.length < 10){
                binding.mobileNo.error = "Mobile No Required"
                binding.mobileNo.requestFocus()
                return@setOnClickListener
            }

            if(bookName == Constants.SELECT){
                binding.bookList.requestFocus()
                requireActivity().toast("Please Select Book")
                return@setOnClickListener
            }

            launch {
                val mbook = Book(name, mobileNo, bookName,false)
                context?.let {

                    if(book == null){

                        bookViewModel.insert(mbook)
                        it.toast("Book Saved")

                    }else{
                        mbook.id = book!!.id
                        bookViewModel.update(mbook)


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