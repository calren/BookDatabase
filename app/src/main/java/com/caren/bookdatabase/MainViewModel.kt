package com.caren.bookdatabase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val bookDao: BookDao) : ViewModel() {

    val allBooks: LiveData<List<Book>> = bookDao.getBooks().asLiveData()

    fun addBook(book: Book) {
        viewModelScope.launch {
            val id = bookDao.insert(book)
            Log.i("Caren", "id inserted: " + id)

            Log.i("Caren", "size: " + allBooks.value?.size)

            Log.i("Caren", "getBooksSlow: " + bookDao.getBooksSlow().size)
        }

    }

    class MainViewModelFactory(
        private val bookDao: BookDao
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(bookDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}