package com.example.marvel

import android.content.Context
import com.example.marvel.ui.MarvelListFragment
import com.example.marvel.ui.MarvelViewModel
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.`when`

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class MarvelListFragment {
    @Mock
    private lateinit var mockContext: Context

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun marvelList() {

    }
}