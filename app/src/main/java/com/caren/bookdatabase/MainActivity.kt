package com.caren.bookdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caren.bookdatabase.database.Book
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel by viewModels {
            MainViewModelFactory((application as BookApplication).database.bookDao())
        }

        // Set an onclicklistener for the button
        // 1. Get the text from the title edittext
        // 2. Get the text from the author edittext
        // 3. Create a new Book object based on the title and the author
        // 4. Save Book object in database

        findViewById<Button>(R.id.button).setOnClickListener {
            val title = findViewById<EditText>(R.id.etBookTitle).text.toString()
            val author = findViewById<EditText>(R.id.etAuthor).text.toString()

            val newBook = Book(title = title, author = author)

            viewModel.addBook(newBook)

        }

        // Add a button to delete the latest / most recent entry
        findViewById<Button>(R.id.deleteBtn).setOnClickListener {
            viewModel.deleteMostRecentBook()

        }

        val rv = findViewById<RecyclerView>(R.id.rv)
        val adapter = BooksAdapter(mutableListOf(), this)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        viewModel.getAllBooks().observe(this) { books ->
            adapter.books = books
            adapter.notifyDataSetChanged()
        }

        // TODO: How to add login
        // 1. When the user opens, we need to check if they're logged in
        // If not, show login screen
        // If yes, we show them the home screen

    }


}