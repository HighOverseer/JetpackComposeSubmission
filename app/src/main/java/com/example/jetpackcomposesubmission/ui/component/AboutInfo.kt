package com.example.jetpackcomposesubmission.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposesubmission.R
import com.example.jetpackcomposesubmission.ui.theme.JetpackComposeSubmissionTheme
import com.example.jetpackcomposesubmission.ui.theme.White100

@Composable
fun AboutInfo(
    modifier: Modifier = Modifier,
    iconResourceId: Int,
    text: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Image(
            painterResource(id = iconResourceId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .background(White100)
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun AboutInfoPreview() {
    JetpackComposeSubmissionTheme {
        AboutInfo(
            iconResourceId = R.drawable.ic_book,
            text = "Computer Engineering Undergraduate at Andalas University"
        )
    }

}
