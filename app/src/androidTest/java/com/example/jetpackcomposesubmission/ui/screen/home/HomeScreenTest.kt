package com.example.jetpackcomposesubmission.ui.screen.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import com.example.jetpackcomposesubmission.R
import com.example.jetpackcomposesubmission.model.getUserPreviews
import com.example.jetpackcomposesubmission.onNodeWithStringId
import com.example.jetpackcomposesubmission.ui.theme.JetpackComposeSubmissionTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeGithubUserPreviews = getUserPreviews()

    @Before
    fun setup() {
        composeTestRule.setContent {
            JetpackComposeSubmissionTheme {
                HomeScreen(
                    navigateToDetail = { },
                    navigateToAbout = { }
                )
            }
        }
    }

    @Test
    fun listData_ShouldDisplayed_validItemsBasedItsSource() {
        composeTestRule.apply {
            activity.apply {
                (fakeGithubUserPreviews.indices).forEach { i ->
                    if (i == 5) {
                        onNodeWithTag(getString(R.string.userlist)).performScrollToIndex(10)
                    }

                    onNodeWithText(fakeGithubUserPreviews[i].name).assertIsDisplayed()
                }
            }
        }
    }

    @Test
    fun listData_ShouldNotDisplayed_offScreenItem() {
        composeTestRule.apply {
            activity.apply {
                onNodeWithTag(getString(R.string.userlist)).performScrollToIndex(0)
                onNodeWithText(fakeGithubUserPreviews.last().name).assertDoesNotExist()
            }
        }
    }

    @Test
    fun listData_ShouldNotDiplayed_invalidItem() {
        composeTestRule.apply {
            activity.apply {
                val fakeInvalidItemTitle = "Test"
                onNodeWithTag(getString(R.string.userlist)).performScrollToIndex(0)
                onNodeWithText(fakeInvalidItemTitle).assertDoesNotExist()
                onNodeWithTag(getString(R.string.userlist)).performScrollToIndex(
                    fakeGithubUserPreviews.lastIndex
                )
                onNodeWithText(fakeInvalidItemTitle).assertDoesNotExist()
            }
        }
    }

    @Test
    fun searchBar_whenSearch_ifRequestedDataExist_ShouldOnlyReturnCorrespondingData(): Unit =
        runBlocking {
            composeTestRule.apply {
                activity.apply {
                    val fakeSearchQuery = "P"
                    onNodeWithStringId(R.string.search_someone)
                        .performClick()
                        .performTextInput(fakeSearchQuery)
                    onNodeWithText(fakeSearchQuery).assertExists()

                    delay(1000L)

                    fakeGithubUserPreviews.forEach {
                        if (it.name.contains(fakeSearchQuery, ignoreCase = true)) {
                            onNodeWithText(it.name).assertIsDisplayed()
                        } else onNodeWithText(it.name).assertDoesNotExist()
                    }
                }
            }
        }

    @Test
    fun searchBar_whenSearch_ifRequestDataNotExist_ShouldDisplayedAllItems(): Unit = runBlocking {
        composeTestRule.apply {
            activity.apply {
                val fakeSearchQuery = "Testest"
                onNodeWithStringId(R.string.search_someone)
                    .performClick()
                    .performTextInput(fakeSearchQuery)
                onNodeWithText(fakeSearchQuery).assertExists()

                delay(1000L)

                (fakeGithubUserPreviews.indices).forEach { i ->
                    if (i == 5) {
                        onNodeWithTag(getString(R.string.userlist)).performScrollToIndex(10)
                    }

                    onNodeWithText(fakeGithubUserPreviews[i].name).assertIsDisplayed()
                }
            }
        }
    }


}