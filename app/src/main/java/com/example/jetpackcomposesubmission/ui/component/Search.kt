package com.example.jetpackcomposesubmission.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposesubmission.R
import com.example.jetpackcomposesubmission.ui.theme.JetpackComposeSubmissionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChanged: (String) -> Unit,
    navigateToAbout: () -> Unit
) {
    val currentOnQueryChanged by rememberUpdatedState(newValue = onQueryChanged)

    SearchBar(
        query = query,
        onQueryChange = currentOnQueryChanged,
        onSearch = { },
        active = false,
        onActiveChange = { },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text(text = stringResource(R.string.search_someone))
        },
        trailingIcon = {
            IconButton(
                onClick = navigateToAbout
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = stringResource(R.string.about_page),
                    modifier = Modifier.size(22.dp)
                )
            }
        },
        shape = MaterialTheme.shapes.medium,
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    JetpackComposeSubmissionTheme {
        Search(
            query = "",
            onQueryChanged = { },
            navigateToAbout = { }
        )
    }
}
