package com.example.pill_note

data class FollowInfo(
    val uid: String,
    val user: User,
    val followers: MutableMap<String, Boolean> = HashMap(),
    val followings: MutableMap<String, Boolean> = HashMap()
)