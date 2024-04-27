package com.example.jetpackcomposesubmission.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposesubmission.ui.theme.JetpackComposeSubmissionTheme

@Composable
fun DetailDescText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = 2,
        fontSize = 16.sp,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Medium
    )
}

@Preview(showBackground = true)
@Composable
fun DetailDescTextPreview() {
    JetpackComposeSubmissionTheme {
        DetailDescText(
            text = "Tokopedia"
        )
    }
}