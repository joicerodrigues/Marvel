package com.example.marvel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marvel.ui.MarvelViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class MarvelListFragment {
    private lateinit var mockContext: Context


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

}
