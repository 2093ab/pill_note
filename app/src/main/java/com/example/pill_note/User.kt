package com.example.pill_note

data class User(val id: String, val email: String, val nickname: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}