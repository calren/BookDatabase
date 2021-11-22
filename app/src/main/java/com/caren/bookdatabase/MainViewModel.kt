package com.caren.bookdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.caren.bookdatabase.database.Book
import com.caren.bookdatabase.database.BookDao
import kotlinx.coroutines.launch

class MainViewModel(val bookDao: BookDao) : ViewModel() {

    fun addBook(book: Book) {
        viewModelScope.launch {
            bookDao.addBook(book)
        }
    }

    fun deleteMostRecentBook() {
        viewModelScope.launch {
            // 1. Grab the latest Book object
            val mostRecentlyAddedBook = bookDao.getMostRecentlyAddedBook()
            // 2. Use the dao to actually delete it
            bookDao.deleteBook(mostRecentlyAddedBook)
        }
    }

    fun getAllBooks(): LiveData<List<Book>> {
        return bookDao.getAllBooks().asLiveData()
    }
}

class MainViewModelFactory(private val bookDao: BookDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(bookDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
