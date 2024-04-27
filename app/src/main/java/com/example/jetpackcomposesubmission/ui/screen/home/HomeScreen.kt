package com.example.jetpackcomposesubmission.ui.screen.home

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposesubmission.R
import com.example.jetpackcomposesubmission.ViewModelFactory
import com.example.jetpackcomposesubmission.di.Injection
import com.example.jetpackcomposesubmission.ui.component.Search
import com.example.jetpackcomposesubmission.ui.component.UserItem
import com.example.jetpackcomposesubmission.ui.getToast

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
    navigateToAbout: () -> Unit
) {
    val query by viewModel.query
    val toastMessageEvent by viewModel.toastMessage
    val userPreviews = viewModel.userPreviews.collectAsState().value

    var toast: Toast? by remember { mutableStateOf(null) }

    LazyColumn(modifier = modifier.testTag(stringResource(R.string.userlist))) {
        stickyHeader {
            Search(
                query = query,
                onQueryChanged = { newQuery ->
                    viewModel.searchUser(query = newQuery)
                },
                navigateToAbout = navigateToAbout
            )
        }
        items(userPreviews, key = { it.id }) { user ->
            UserItem(
                userPreview = user,
                modifier = Modifier.clickable { navigateToDetail(user.id) }
            )
        }
    }

    toastMessageEvent.getValue()?.let {
        toast?.cancel()
        toast = getToast(LocalContext.current, it)
        toast?.show()
    }
}


