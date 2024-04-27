package com.example.jetpackcomposesubmission.model

import com.example.jetpackcomposesubmission.R

data class UserPreview(
    val id: Long,
    val username: String,
    val name: String,
    val avatar: Int,
)


fun getUserPreviews(): List<UserPreview> {
    return listOf(
        UserPreview(
            1,
            "@Markk",
            "Mark Zuheif",
            avatar = R.drawable.user_1
        ),
        UserPreview(
            2,
            "@MikaelaFR",
            "Mikaela Frans",
            avatar = R.drawable.user_2
        ),
        UserPreview(
            3,
            "@rgosling",
            "Ryan Gosling",
            avatar = R.drawable.user_3
        ),
        UserPreview(
            4,
            "@PeterP",
            "Peter Parker",
            avatar = R.drawable.user_4
        ),
        UserPreview(
            5,
            "@MelindaL",
            "Melinda Lauren",
            avatar = R.drawable.user_5
        ),
        UserPreview(
            6,
            "@SamSep",
            "Sam Sepiol",
            avatar = R.drawable.user_6
        ),
        UserPreview(
            7,
            "@GraceER",
            "Grace Enro",
            avatar = R.drawable.user_7
        ),
        UserPreview(
            8,
            "@SonyaMO",
            "Sonya Moore",
            avatar = R.drawable.user_8
        ),
        UserPreview(
            9,
            "@SandyMn",
            "Sandy Maine",
            avatar = R.drawable.user_9
        ),
        UserPreview(
            10,
            "@Fikiki",
            "Fiki Naki",
            avatar = R.drawable.user_10
        )
    )
}