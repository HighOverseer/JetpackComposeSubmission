package com.example.jetpackcomposesubmission.model

private var idIncr: Long = 0
    get() {
        field += 1
        return field
    }

data class RepositoryPreview(
    val id: Long,
    val name: String,
    val url: String
)


fun generateRepositoryList(
    repoNames: List<String>,
    username: String,
): List<RepositoryPreview> {
    return List(repoNames.size) { i ->
        RepositoryPreview(
            id = idIncr,
            repoNames[i],
            getGithubLink(username, repoNames[i])
        )
    }
}


private fun getGithubLink(
    username: String,
    repoName: String
) = "https://github.com/${username.substring(1, username.length - 1)}/$repoName"