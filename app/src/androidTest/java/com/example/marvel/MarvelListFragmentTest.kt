package com.example.marvel

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.testing.TestNavHostController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.marvel.network.MarvelCharacters
import com.example.marvel.network.Thumbnail
import com.example.marvel.ui.*
import io.mockk.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.dsl.module


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class) // dizendo que irá executar esta classe e indicando que a mesma é uma classe de teste
class MarvelListFragmentTest : BaseTest() {

    // Create a TestNavHostController
    private val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext()
    )

    private val viewModel: MarvelViewModel = spyk()

    private val instrumentedTestModule = module {
        single { viewModel }
    }

    @get:Rule
    val koinTestRule = KoinTestRule(
        modules = listOf(instrumentedTestModule)
    )

    private val marvelCharacters = listOf(
        MarvelCharacters(
            "3-D Man",
            " ",
            thumbnail = Thumbnail(
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                "jpg"
            )
        )
        , MarvelCharacters(
            "A-Bomb (HAS)",
            " ",
            thumbnail = Thumbnail(
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                "jpg"
            )
        )
        , MarvelCharacters(
            "A.I.M.",
            " ",
            thumbnail = Thumbnail(
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                "jpg"
            )
        )
        , MarvelCharacters(
            "Aaron Stack",
            " ",
            thumbnail = Thumbnail(
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                "jpg"
            )
        )
    )

    @Before
    fun setup() {
//        coEvery {
//            viewModel.marvelApiService.getMarvel()
//        } returns MarvelListResponse(
//            MarvelResults(
//                listOf(
//                    MarvelCharacters(
//                        "Aaron Stack",
//                        "joão está correndo",
//                        thumbnail = Thumbnail(
//                            "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
//                            "jpg"
//                        )
//                    ), MarvelCharacters(
//                        "Aaron Stack",
//                        "joão está correndo",
//                        thumbnail = Thumbnail(
//                            "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
//                            "jpg"
//                        )
//                    )
//                )
//            )
//        )

        launchFragment()
    }

    @After
    fun tearDown(){
        stopKoin()
    }

    private fun launchFragment() {
        launchFragmentInContainer<MarvelListFragment>(themeResId = R.style.Theme_Marvel)
    }

    @Test
    fun useAppContext() {


        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.marvel", appContext.packageName)
        Thread.sleep(2000)
    }

    @Test
    fun checkName() {
        Thread.sleep(2000)
        onView(withId(R.id.recycler_view))
            .check(matches(atPositionOnView(R.id.marvel_name, 0, withText("3-D Man"))))
            .check(matches((isDisplayed())))
    }

    @Test
    fun cardClick() {
        Thread.sleep(2000)
        // Verify that performing a click changes the NavController’s state
        waitForView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MarvelListAdapter.MarvelViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(2000)
        assertEquals(navController.currentDestination?.id, R.id.marvelDetailFragment)
    }

    @Test
    fun swipeScroll() {
        Thread.sleep(2000)
        marvelCharacters.forEachIndexed{i,c ->

        onView(withId(R.id.recycler_view))
            .perform(swipeUp()) // swipe up
        onView(withId(R.id.recycler_view))
            .check(
                matches(
                    atPositionOnView(
                        R.id.marvel_name, i, withText(c.name)
                    )
                )
            )
            .check(matches((isDisplayed()))) // verifica o item
        }
        verify(exactly = 1) {
            viewModel.getMarvelList()
        }

    }

    @Test
    fun swipeRefresh() {
        Thread.sleep(2000)

//        marvelCharacters.forEachIndexed { i, c ->
//            onView(withId(R.id.recycler_view))
//                .perform(swipeUp()) // swipe up
//            waitForView(withId(R.id.recycler_view))
//                .check(matches(atPositionOnView(R.id.marvel_name, i, withText(c.name))))
//                .check(matches((isDisplayed())))
//
//        }

        waitForView(withId(R.id.constraint)).perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)))
        Thread.sleep(2000)

//        onView(withId(R.id.SwipeRefresh)).perform(swipeDown())
//        Thread.sleep(2000)
//        onView(withId(R.id.SwipeRefresh)).perform(scrollTo(), click())
        verify(exactly = 2) {
            viewModel.getMarvelList()
        }
    }
}


//
//    @Test
//    fun checkDescription(){
//        onView(withId(R.id.card)).perform(click()) //indicando que era fazer uma ação em card e esta ação é de click
//        onView(withId(R.id.description)).check(matches(withText("")))
//    }
//
//    fun tiposDeTestes(){
//        onView(withId(R.id.sendButton)).perform(typeText("test"), click())
//        // withId(R.id.my_view) é um ViewMatcher
//        // click() é uma ViewAction
//        // matches(isDisplayed()) é uma ViewAssertion
//        onView(withId(R.id.description))
//            .perform(click())
//            .check(matches(isDisplayed()))
//
//        //verifica texto vindo do btn
//        onView(withId(R.id.sendButton)).perform(typeText("test"), click())
//
//        //verifica scroll
//        onView(withId(R.id.SwipeRefresh)).perform(scrollTo(), click())
//
//        //verifica o texto de textview
//        onView(withId(R.id.marvel_name))
//        //vai verificar o texto do conteudo
//        onView(withId(R.id.marvel_name)).check(matches(withText("Hello Espresso!")))
//
//        //clica no botão
//        onView(withId(R.id.sendButton))
//        //executa o clique
//        onView(withId(R.id.sendButton)).perform(click())
//
//        //clica no botão
//        onView(withId(R.id.recycler_view))
//        //executa o clique
//        onView(withId(R.id.recycler_view)).perform(click())
//
//
//        //selecionar um item
//        onData(allOf(`is`(instanceOf(String::class.java)),
//            `is`("Americano"))).perform(click())
//
//        //verifica se o texto está correto
//        onView(withId(R.id.description))
//            .check(matches(withText(containsString("Americano"))))
//
//        //clicar na visualização
//        onView(withId(R.id.card)).perform(click())
//
//
//    }
//    @Test
//    fun verifiedClick(){
//        rule.scenario.onActivity {
//        onView(withId(R.id.card)).perform(click())  //indicando que era fazer uma ação em card e esta ação é de click
//        onView(withId(R.id.marvel_name))
//            .check(matches(isDisplayed()))
//        }
//    }
//    @Test
//    fun verifiedText(){
//        onView(withId(R.id.card)).perform(click()) //indicando que era fazer uma ação em card e esta ação é de click
//        onView(withId(R.id.sendButton)).perform(click()) //indicando que era fazer uma ação em sendButton e esta ação é de click
//    }