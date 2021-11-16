package com.qfc.applicationmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qfc.applicationmvvm.addbook.ui.AddBookActivity
import com.qfc.applicationmvvm.databinding.ActivityMainBinding
import com.qfc.applicationmvvm.downloadpdf.ui.PDFActivity
import com.qfc.applicationmvvm.randomlist.ui.ListActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addBook.setOnClickListener {
            startNewActivity( AddBookActivity::class.java)
        }

        binding.downLoadPDF.setOnClickListener {
            startNewActivity(PDFActivity::class.java)
        }

        binding.randomList.setOnClickListener {
            startNewActivity( ListActivity::class.java)
        }

    }
}