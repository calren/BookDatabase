package com.caren.bookdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel by viewModels {
            MainViewModel.MainViewModelFactory((application as BookApplication).database.bookDao())
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            val bookTitle = findViewById<EditText>(R.id.etBookTitle).text.toString()
            val author = findViewById<EditText>(R.id.etAuthor).text.toString()

            val newBook = Book(title = bookTitle, author = author)

            viewModel.addBook(newBook)

        }

        viewModel.allBooks.observe(this) { books ->
            findViewById<Button>(R.id.btnGetBooks).text = "Number of books: " + books.size
        }
    }
}