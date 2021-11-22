package com.caren.bookdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.caren.bookdatabase.database.Book
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set an onclicklistener for the button
        // 1. Get the text from the title edittext
        // 2. Get the text from the author edittext
        // 3. Create a new Book object based on the title and the author
        // 4. Save Book object in database

        findViewById<Button>(R.id.button).setOnClickListener {
            val title = findViewById<EditText>(R.id.etBookTitle).text.toString()
            val author = findViewById<EditText>(R.id.etAuthor).text.toString()

            val newBook = Book(title = title, author = author)


            lifecycleScope.launch {
                (application as BookApplication)
                    .database.bookDao().addBook(newBook)
            }

        }
    }


}