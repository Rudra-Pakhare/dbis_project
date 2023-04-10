package com.example.ignite.models

data class User(
    val id: String = "",
    val isAnonymous: Boolean = true,
    val name: String? = "Guest"
)
