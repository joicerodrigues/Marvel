package com.example.marvel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.runner.Description
import org.junit.rules.TestWatcher

class CoroutinesTestRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}

/**
 * Creates a new [CoroutineScope] with the rule's testDispatcher
 */
fun CoroutinesTestRule.CoroutineScope(): CoroutineScope = TestScope(testDispatcher)