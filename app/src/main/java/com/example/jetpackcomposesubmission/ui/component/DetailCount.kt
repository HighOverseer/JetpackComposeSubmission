package com.example.jetpackcomposesubmission.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposesubmission.ui.theme.JetpackComposeSubmissionTheme

@Composable
fun DetailCount(
    modifier: Modifier = Modifier,
    info: String,
    count: Int
) {
    val countText = count.toString()
    Column(
        modifier = modifier
            .padding(horizontal = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = countText)
        Text(
            text = info,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailCountPreview() {
    JetpackComposeSubmissionTheme {
        DetailCount(
            info = "Following",
            count = 8
        )
    }
}