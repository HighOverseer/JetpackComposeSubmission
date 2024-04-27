package com.example.jetpackcomposesubmission.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import com.example.jetpackcomposesubmission.R
import com.example.jetpackcomposesubmission.model.getUsers
import com.example.jetpackcomposesubmission.ui.theme.JetpackComposeSubmissionTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeUserDetail = getUsers().first()

    @Before
    fun setup() {
        composeTestRule.setContent {
            JetpackComposeSubmissionTheme {
                DetailScreen(
                    selectedUserId = fakeUserDetail.userPreview.id,
                    navigateBackToHome = { }
                )
            }
        }
    }

    @Test
    fun allUserDetailData_shouldBeDisplayed() {
        composeTestRule.apply {
            activity.apply {


                val firstName = fakeUserDetail.userPreview.name.split(" ").first()
                val expectedTopBarTitle = "$firstName's Profile"
                onNodeWithText(expectedTopBarTitle).assertIsDisplayed()

                val expectedTagFavoriteButton = if (fakeUserDetail.isFavorite) {
                    getString(R.string.user_favorite)
                } else getString(R.string.not_user_favorite)
                onNodeWithContentDescription(
                    getString(R.string.buttonfavorite),
                    useUnmergedTree = true
                ).assert(hasTestTag(expectedTagFavoriteButton))

                onNodeWithContentDescription(
                    getString(R.string.user_avatar)
                ).assert(hasTestTag(fakeUserDetail.userPreview.avatar.toString()))

                onNodeWithText(fakeUserDetail.followerCount.toString()).assertIsDisplayed()
                onNodeWithText(fakeUserDetail.followingCount.toString()).assertIsDisplayed()
                onNodeWithText(fakeUserDetail.repositoriesCount.toString()).assertIsDisplayed()
                onNodeWithText(fakeUserDetail.address).assertIsDisplayed()
                onNodeWithText(fakeUserDetail.companyName).assertIsDisplayed()
                onNodeWithText(fakeUserDetail.userPreview.name).assertIsDisplayed()
                onNodeWithTag(getString(R.string.listrepositories)).assertIsDisplayed()

                (fakeUserDetail.repoPreviews.indices).forEach { i ->
                    if (i == 5) {
                        onNodeWithTag(getString(R.string.listrepositories)).performScrollToIndex(
                            fakeUserDetail.repoPreviews.lastIndex
                        )
                    }

                    onNodeWithText(
                        fakeUserDetail.repoPreviews[i].name
                    ).assertIsDisplayed()
                }
            }
        }
    }
}