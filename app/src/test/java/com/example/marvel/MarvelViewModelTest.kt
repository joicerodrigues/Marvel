package com.example.marvel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.test.internal.runner.storage.RunnerTestStorageIO
import com.example.marvel.network.*
import com.example.marvel.ui.MarvelDetailFragment
import com.example.marvel.ui.MarvelViewModel
import com.example.marvel.ui.marvelModule
import io.mockk.MockKStubScope
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*
import org.mockito.Mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

//@ExtendWith(MockKExtension::class)
class MarvelViewModelTest : BaseTest() {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()
    //   @MockK
    // private val marvelApiServiceMock: MarvelApiService.MarvelApiService = mockk()
//    private val resultGetMarvel = marvelApiServiceMock
//    private val interfaceTest = mockk<MarvelApiService>()

    private lateinit var viewModel: MarvelViewModel

    private val marvelCharacterFake =
        MarvelCharacters(
            "3D - Man",
            " ",
            thumbnail = Thumbnail
                (
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                "jpg"
            )
        )

    @Before
    fun setup() {
     //   Dispatchers.setMain(dispatcher)
      //  viewModel = MarvelViewModel()
    }

    @After
    fun tearDown() {
       // Dispatchers.resetMain()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun marvelClickedTest() = runTest{
       // val dispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())
      //  Dispatchers.setMain(dispatcher)

            viewModel = MarvelViewModel()
            delay(5000)
            viewModel.marvelClicked(marvelCharacterFake)
            viewModel.marvel.observeForever { }
            val listMarvel = viewModel.marvel.getOrAwaitValue()
            delay(5000)
            //   val lista = viewModel.marvelApiService.getMarvel().data.results
            assertEquals(marvelCharacterFake, listMarvel)

    }


//        equals(listCharacters, listMarvel)
//        val listApimock =


//    @Test
//    fun listCharacters() {
//        lateinit var mockContext: Context
//        val viewModel = MarvelViewModel()
//
//        val listMarvel = viewModel.marvel.value
//
//        assertEquals()
//
//    }

//    chamar viewmodel
//    mocka resposta do datasource (MarvelApiService)
//    e comparar as duas
//    companion object {
//        private const val FAKE_STRING = "HELLO_WORLD"
//    }
}

