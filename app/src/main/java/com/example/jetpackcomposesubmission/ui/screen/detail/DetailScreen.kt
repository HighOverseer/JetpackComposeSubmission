package com.example.jetpackcomposesubmission.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.jetpackcomposesubmission.R
import com.example.jetpackcomposesubmission.ViewModelFactory
import com.example.jetpackcomposesubmission.di.Injection
import com.example.jetpackcomposesubmission.model.User
import com.example.jetpackcomposesubmission.model.UserPreview
import com.example.jetpackcomposesubmission.model.generateRepositoryList
import com.example.jetpackcomposesubmission.ui.component.DetailCount
import com.example.jetpackcomposesubmission.ui.component.DetailDescText
import com.example.jetpackcomposesubmission.ui.component.RepositoryItem
import com.example.jetpackcomposesubmission.ui.getToast
import com.example.jetpackcomposesubmission.ui.theme.JetpackComposeSubmissionTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    selectedUserId: Long,
    navigateBackToHome: () -> Unit
) {
    val selectedUser = viewModel.selectedUserDetail.collectAsState().value
    val toastMessageEvent by viewModel.toastMessage

    if (selectedUser != null) {
        DetailContent(
            modifier = modifier,
            selectedUser = selectedUser,
            navigateBackToHome,
            onChangeFavoriteStatus = {
                viewModel.changeUserFavoriteStatus(
                    selectedUserId
                )
            }
        )
    }

    toastMessageEvent.getValue()?.let { message ->
        getToast(
            LocalContext.current,
            message
        ).show()
    }

    LaunchedEffect(key1 = true, block = {
        viewModel.getUserDetailById(selectedUserId)
    })
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    selectedUser: User,
    navigateBackToHome: () -> Unit,
    onChangeFavoriteStatus: () -> Unit
) {
    val userPreview = selectedUser.userPreview
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {

        DetailTopBar(
            navigateBackToHome = navigateBackToHome,
            selectedUserName = selectedUser.firstName,
            isSelectedUserFavorite = selectedUser.isFavorite,
            onChangeFavoriteStatus = onChangeFavoriteStatus
        )
        Spacer(modifier = Modifier.height(14.dp))

        DetailHeadSection(
            userAvatar = userPreview.avatar,
            repositoryCount = selectedUser.repositoriesCount,
            followingCount = selectedUser.followingCount,
            followerCount = selectedUser.followerCount
        )

        Spacer(modifier = Modifier.height(16.dp))
        DetailDescText(
            text = userPreview.name,
        )
        DetailDescText(
            text = selectedUser.companyName,
        )
        DetailDescText(
            text = selectedUser.address
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "List Repositories",
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .height(1.dp)
                .background(Color.Gray)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .testTag(stringResource(R.string.listrepositories)),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(selectedUser.repoPreviews, key = { it.id }) { repoPreview ->
                RepositoryItem(repositoryPreview = repoPreview)
            }
        }
    }
}

@Composable
fun DetailTopBar(
    modifier: Modifier = Modifier,
    navigateBackToHome: () -> Unit,
    selectedUserName: String,
    isSelectedUserFavorite: Boolean,
    onChangeFavoriteStatus: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            content = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_to_home),
                    modifier = Modifier
                        .size(42.dp),
                    tint = Color.Gray
                )
            },
            onClick = navigateBackToHome,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = "$selectedUserName's Profile",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(
            onClick = onChangeFavoriteStatus
        ) {
            Icon(
                imageVector = if (isSelectedUserFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = stringResource(R.string.buttonfavorite),
                modifier = Modifier
                    .size(28.dp)
                    .testTag(
                        if (isSelectedUserFavorite) {
                            stringResource(R.string.user_favorite)
                        } else stringResource(R.string.not_user_favorite)
                    )
            )
        }
    }
}

@Composable
fun DetailHeadSection(
    userAvatar: Int,
    repositoryCount: Int,
    followingCount: Int,
    followerCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = userAvatar,
            contentDescription = stringResource(R.string.user_avatar),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .testTag(userAvatar.toString()),
        )
        DetailCount(
            info = "Repositories",
            count = repositoryCount
        )
        DetailCount(
            info = "Followers",
            count = followerCount
        )
        DetailCount(
            info = "Followings",
            count = followingCount
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    JetpackComposeSubmissionTheme {
        DetailContent(
            selectedUser = User(
                userPreview = UserPreview(
                    1,
                    "@fajaralif",
                    "Fajar Alif Riyandi",
                    avatar = R.drawable.user_1
                ),
                100,
                44,
                14,
                "GoTo",
                "Padang, West Sumatra",
                generateRepositoryList(
                    listOf(
                        "QuantumPanda",
                        "CyberSphinx",
                        "NebulaForge",
                        "CryptoHarmony",
                        "BioGizmo",
                        "SynthWaveAI",
                        "SolarFlareOS",
                        "AeroSwift",
                        "ZenithBot",
                        "BlazeNavigator"
                    ),
                    "@fajaralif"
                )
            ),
            navigateBackToHome = { },
            onChangeFavoriteStatus = { }
        )
    }
}