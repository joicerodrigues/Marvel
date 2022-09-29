package com.example.marvel

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.marvel", appContext.packageName)
    }
    fun teste(){
        // withId(R.id.my_view) é um ViewMatcher
        // click() é uma ViewAction
        // matches(isDisplayed()) é uma ViewAssertion
        onView(withId(R.id.description))
            .perform(click())
            .check(matches(isDisplayed()))

        //verifica texto vindo do btn
        onView(withId(R.id.sendButton)).perform(typeText("test"), click())

        //verifica scroll
        onView(withId(R.id.SwipeRefresh)).perform(scrollTo(), click())

        //verifica o texto de textview
        onView(withId(R.id.marvel_name))
        //vai verificar o texto do conteudo
        onView(withId(R.id.marvel_name)).check(matches(withText("Hello Espresso!")))

        //clica no botão
        onView(withId(R.id.sendButton))
        //executa o clique
        onView(withId(R.id.sendButton)).perform(click())

        //clica no botão
        onView(withId(R.id.recycler_view))
        //executa o clique
        onView(withId(R.id.recycler_view)).perform(click())


        //selecionar um item
        onData(allOf(`is`(instanceOf(String::class.java)),
            `is`("Americano"))).perform(click())

        //verifica se o texto está correto
        onView(withId(R.id.description))
            .check(matches(withText(containsString("Americano"))))

        //clicar na visualização
        onView(withId(R.id.card)).perform(click())


    }


}