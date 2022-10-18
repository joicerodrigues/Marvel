package com.example.marvel

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.marvel.ui.MarvelListAdapter
import com.example.marvel.ui.MarvelListFragment
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class) // dizendo que irá executar esta classe e indicando que a mesma é uma classe de teste
class MarvelListFragmentTest : BaseTest() {

//    @get:Rule
//    val intentsTestRule = IntentsTestRule(MainActivity::class.java)


    // Create a TestNavHostController
    val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext()
    )

    @Before
    fun setup() {
        // Create a graphical FragmentScenario for the TitleScreen
        val marvelListFragmentScenario =
            launchFragmentInContainer<MarvelListFragment>(themeResId = R.style.Theme_Marvel)

        marvelListFragmentScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.nav_graph)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
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
        assertEquals(navController.currentDestination?.id, R.id.marvelDetailFragment)
    }

    @Test
    fun swipeScroll(){
        Thread.sleep(2000)
        onView(withId(R.id.recycler_view)).perform(swipeUp())
        Thread.sleep(2000)
        onView(withId(R.id.recycler_view)).perform(swipeDown())
        Thread.sleep(2000)

    }

//    @Test
//    fun Intents(){
//        val resultData = Intent()
//        val detailTitle = "3-D Man"
//        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
//        Thread.sleep(2000)
//        onView(withId(R.id.card)).perform(click())
//        Thread.sleep(2000)
//        onView(withId(R.id.marvel_name)).check(matches(withText(detailTitle)))
//
//    }
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