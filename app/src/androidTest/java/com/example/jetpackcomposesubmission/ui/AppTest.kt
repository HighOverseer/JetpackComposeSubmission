package com.example.jetpackcomposesubmission.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.jetpackcomposesubmission.R
import com.example.jetpackcomposesubmission.assertCurrentRouteName
import com.example.jetpackcomposesubmission.model.getUserPreviews
import com.example.jetpackcomposesubmission.model.getUsers
import com.example.jetpackcomposesubmission.onNodeWithStringId
import com.example.jetpackcomposesubmission.ui.navigation.Screen
import com.example.jetpackcomposesubmission.ui.theme.JetpackComposeSubmissionTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    private val fakeGithubUserPreviews = getUserPreviews()
    private val fakeGithubUsers = getUsers()

    @Before
    fun setup() {
        composeTestRule.setContent {
            JetpackComposeSubmissionTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                App(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.apply {
            activity.apply {
                val selectedItemIndex = fakeGithubUserPreviews.lastIndex
                onNodeWithTag(getString(R.string.userlist)).performScrollToIndex(selectedItemIndex)
                onNodeWithText(fakeGithubUserPreviews[selectedItemIndex].name).performClick()
                navController.assertCurrentRouteName(Screen.Detail.route)
                onNodeWithText(fakeGithubUsers[selectedItemIndex].companyName).assertIsDisplayed()
                onNodeWithContentDescription(
                    getString(R.string.back_to_home)
                ).performClick()
                navController.assertCurrentRouteName(Screen.Home.route)
            }
        }
    }

    @Test
    fun navHost_verifyNavigateToAboutAndNavigateBack() {
        composeTestRule.apply {
            activity.apply {
                onNodeWithContentDescription(
                    getString(R.string.about_page)
                ).performClick()
                navController.assertCurrentRouteName(Screen.About.route)

                onNodeWithStringId(R.string.fajar_alif_riyandi).assertIsDisplayed()
                onNodeWithContentDescription(
                    getString(R.string.back_to_home)
                ).performClick()
                navController.assertCurrentRouteName(Screen.Home.route)
                onNodeWithTag(
                    getString(R.string.userlist)
                ).assertIsDisplayed()
            }
        }
    }

    @Test
    fun navHost_verifyAddingAndRemovingUserToFromFavorite() {
        composeTestRule.apply {
            activity.apply {
                val selectedItemIndex = fakeGithubUserPreviews.lastIndex
                val selectedItemPreview = fakeGithubUserPreviews[selectedItemIndex]
                val selectedItem = fakeGithubUsers[selectedItemIndex]


                onNodeWithTag(getString(R.string.userlist)).performScrollToIndex(selectedItemIndex)
                onNodeWithText(selectedItemPreview.name).performClick()
                navController.assertCurrentRouteName(Screen.Detail.route)
                onNodeWithText(selectedItem.companyName).assertIsDisplayed()
                onNodeWithContentDescription(
                    getString(R.string.buttonfavorite),
                    useUnmergedTree = true
                ).assert(hasTestTag(getString(R.string.not_user_favorite)))
                onNodeWithContentDescription(
                    getString(R.string.buttonfavorite)
                ).performClick()
                onNodeWithContentDescription(
                    getString(R.string.buttonfavorite),
                    useUnmergedTree = true
                ).assert(hasTestTag(getString(R.string.user_favorite)))
                onNodeWithContentDescription(
                    getString(R.string.back_to_home)
                ).performClick()
                navController.assertCurrentRouteName(Screen.Home.route)
                onNodeWithTag(getString(R.string.userlist)).performScrollToIndex(selectedItemIndex)
                onNodeWithText(selectedItemPreview.name).performClick()
                onNodeWithContentDescription(
                    getString(R.string.buttonfavorite),
                    useUnmergedTree = true
                ).assert(hasTestTag(getString(R.string.user_favorite)))


                onNodeWithContentDescription(
                    getString(R.string.buttonfavorite)
                ).performClick()
                onNodeWithContentDescription(
                    getString(R.string.buttonfavorite),
                    useUnmergedTree = true
                ).assert(hasTestTag(getString(R.string.not_user_favorite)))
                onNodeWithContentDescription(
                    getString(R.string.back_to_home)
                ).performClick()
                navController.assertCurrentRouteName(Screen.Home.route)
                onNodeWithTag(getString(R.string.userlist)).performScrollToIndex(selectedItemIndex)
                onNodeWithText(selectedItemPreview.name).performClick()
                onNodeWithContentDescription(
                    getString(R.string.buttonfavorite),
                    useUnmergedTree = true
                ).assert(hasTestTag(getString(R.string.not_user_favorite)))
            }
        }
    }

}