package com.example.jetpackcomposesubmission.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposesubmission.model.RepositoryPreview
import com.example.jetpackcomposesubmission.model.getUsers
import com.example.jetpackcomposesubmission.ui.theme.JetpackComposeSubmissionTheme

@Composable
fun RepositoryItem(
    modifier: Modifier = Modifier,
    repositoryPreview: RepositoryPreview
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = repositoryPreview.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = repositoryPreview.url,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryItemPreview() {
    JetpackComposeSubmissionTheme {
        RepositoryItem(
            repositoryPreview = getUsers()[0].repoPreviews[0]
        )
    }
}