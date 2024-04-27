package com.example.jetpackcomposesubmission.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetpackcomposesubmission.R
import com.example.jetpackcomposesubmission.ui.component.AboutInfo
import com.example.jetpackcomposesubmission.ui.theme.JetpackComposeSubmissionTheme
import com.example.jetpackcomposesubmission.ui.theme.White100

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navigateBackToHome: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        IconButton(
            modifier = Modifier
                .padding(start = 8.dp, top = 16.dp),
            content = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back_to_home),
                    modifier = Modifier
                        .size(42.dp),
                    tint = Color.Gray
                )
            },
            onClick = navigateBackToHome,
        )

        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxHeight()
        ) {
            AboutHeaderContent()
            Spacer(modifier = Modifier.height(40.dp))
            AboutInfo(
                iconResourceId = R.drawable.ic_book,
                text = "Computer Engineering Undergraduate at Andalas University"
            )
            AboutInfo(
                iconResourceId = R.drawable.ic_marker,
                text = "West Sumatra, Indonesia"
            )
            AboutInfo(
                iconResourceId = R.drawable.ic_hand,
                text = "Android Enthusiast"
            )
            AboutInfo(
                iconResourceId = R.drawable.ic_tag,
                text = "\" Discipline and Perseverance is the Key! \""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    JetpackComposeSubmissionTheme {
        AboutScreen(navigateBackToHome = { })
    }
}


@Composable
fun AboutHeaderContent(
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, White100, MaterialTheme.shapes.medium),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = R.drawable.fajar,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = stringResource(R.string.fajar_alif_riyandi),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = stringResource(R.string.fajariyandi36_gmail_com),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
    }
}
