package com.jyotimoykashyap.coughittest.model

data class TodoItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)