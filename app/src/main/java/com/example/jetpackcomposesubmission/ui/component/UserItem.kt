package com.example.jetpackcomposesubmission.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.jetpackcomposesubmission.R
import com.example.jetpackcomposesubmission.model.UserPreview
import com.example.jetpackcomposesubmission.ui.theme.JetpackComposeSubmissionTheme
import com.example.jetpackcomposesubmission.ui.theme.White100

@Composable
fun UserItem(
    userPreview: UserPreview,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
            .padding(
                vertical = 4.dp,
                horizontal = 16.dp
            )
            .border(1.dp, White100, MaterialTheme.shapes.medium)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),

            ) {

            AsyncImage(
                model = userPreview.avatar,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clip(CircleShape)
                    .size(64.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = userPreview.username,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = userPreview.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun UserItemPreview() {
    JetpackComposeSubmissionTheme {
        UserItem(
            UserPreview(
                1,
                "",
                "",
                R.drawable.user_1
            )
        )
    }
}