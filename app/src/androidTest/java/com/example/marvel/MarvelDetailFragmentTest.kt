package com.example.marvel

import android.content.Intent
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.ViewModelProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.marvel.network.MarvelCharacters
import com.example.marvel.network.Thumbnail
import com.example.marvel.ui.MarvelDetailFragment
import com.example.marvel.ui.MarvelListFragment
import com.example.marvel.ui.MarvelViewModel
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Test


class MarvelDetailFragmentTest() : BaseTest() {

    private val fragmentFactoryMock: FragmentFactory = mockk()
    private val name: String = "joão"
    private val description: String = "joão está correndo"

    @Before
    fun setup() {
        val viewModel: MarvelViewModel = mockk(relaxed = true)

        val viewModelFactoryMock: ViewModelProvider.Factory = mockk()

        every {
            viewModel.marvel.value
        } returns MarvelCharacters(
            "joão",
            "joão está correndo",
            thumbnail = Thumbnail(
                "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                "jpg"
            )
        )

        every {
            viewModelFactoryMock.create(
                MarvelViewModel::class.java,
                any()
            )
        } answers { viewModel }
        every { fragmentFactoryMock.instantiate(any(), any()) } answers {
            MarvelDetailFragment {
                viewModelFactoryMock
            }
        }
    }

    @Test
    fun checkClick() {
        launchFragmentInContainer<MarvelDetailFragment>(
            themeResId = R.style.Theme_Marvel,
            factory = fragmentFactoryMock
        )
        Thread.sleep(2000)
        //executa o clique
        onView(withId(R.id.sendButton)).perform(click())
    }

    @Test
    fun checkDescription() {
        launchFragmentInContainer<MarvelDetailFragment>(
            themeResId = R.style.Theme_Marvel,
            factory = fragmentFactoryMock
        )
        Thread.sleep(2000)
        //confere descrição do elemento
        onView(withId(R.id.description))
            .check(matches(withText("joão está correndo")))
        Thread.sleep(2000)
    }

    @Test
    fun checkTextBtn() {
        launchFragmentInContainer<MarvelDetailFragment>(
            themeResId = R.style.Theme_Marvel,
            factory = fragmentFactoryMock
        )
        Thread.sleep(2000)
        onView(withId(R.id.sendButton))
            .check(matches(withText(containsString("SEND FOR A FRIEND"))))
    }

    @Test
    fun shareButtonTestIntent() {
        // GIVEN
        val intent: Intent =
            Intent().apply { //criando intent que faz a ação de compartilhar os dados
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, description)
                putExtra(Intent.EXTRA_TITLE, name)
                type = "text/plain"
            }

        Intents.init()
        intending(hasAction(Intent.ACTION_SEND)).respondWith(mockk()) // mockando intent

        // WHEN
        launchFragmentInContainer<MarvelDetailFragment>(
            themeResId = R.style.Theme_Marvel,
            factory = fragmentFactoryMock
        ) // iniciando fragment

        Thread.sleep(2000)
        // THEN
        onView(withId(R.id.sendButton)).perform(click()) // perfomando um click
        Thread.sleep(2000)
        intended(hasAction(Intent.ACTION_CHOOSER))
        intended(hasExtra(matchesString(Intent.EXTRA_INTENT), matchesIntent(intent)))
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