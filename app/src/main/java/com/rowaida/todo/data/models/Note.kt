package com.rowaida.todo.data.models

data class Note (
    val id: Int,
    val username: String,
    val note: String,
    val status: Status
)