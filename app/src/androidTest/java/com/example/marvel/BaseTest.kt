package com.example.marvel

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.util.TreeIterables
import org.hamcrest.Description
import org.hamcrest.Matcher
import java.lang.Exception

open class BaseTest {

    companion object {
        fun lookFor(matcher: Matcher<View>): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return ViewMatchers.isRoot()
                }

                override fun getDescription(): String {
                    return "Looking for $matcher"
                }

                override fun perform(uiController: UiController?, view: View?) {
                    var attempts = 0
                    val childViews: Iterable<View> = TreeIterables.breadthFirstViewTraversal(view)
                    childViews.forEach {
                        attempts++
                        if (matcher.matches(it)) {
                            return
                        }
                    }

                    throw NoMatchingViewException.Builder()
                        .withRootView(view)
                        .withViewMatcher(matcher)
                        .build()
                }
            }
        }
    }

    fun waitForView(matcher: Matcher<View>,
                    timeoutMillis: Int = 5000,
                    attemptTimeoutMillis: Long = 100
    ): ViewInteraction {
        val maxAttempts = timeoutMillis / attemptTimeoutMillis.toInt()
        var attempts = 0
        for (i in 0..maxAttempts) {
            try {
                attempts++
                Espresso.onView(ViewMatchers.isRoot()).perform(lookFor(matcher))
                return Espresso.onView(matcher)
            } catch (e: Exception) {
                if (attempts == maxAttempts) {
                    throw e
                }
                Thread.sleep(attemptTimeoutMillis)
            }
        }
        throw Exception("Could not find a view matching $matcher")
    }


    fun atPositionOnView(
        @IdRes idRes: Int,
        position: Int,
        itemMatcher: Matcher<View?>
    ): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has view id $itemMatcher at position $position")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                val targetView = viewHolder?.itemView?.findViewById<View>(idRes)
                return itemMatcher.matches(targetView)
            }
        }
    }

}
