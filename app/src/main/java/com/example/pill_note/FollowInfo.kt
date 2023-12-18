package com.example.pill_note

data class FollowInfo(
    val uid: String? = null,
    val user: User? = null,
    val followers: MutableMap<String, Boolean> = HashMap(),
    val followings: MutableMap<String, Boolean> = HashMap()
)