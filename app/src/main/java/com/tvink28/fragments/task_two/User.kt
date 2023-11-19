package com.tvink28.fragments.task_two

import java.io.Serializable

data class User(
    val id: Int,
    val photo: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
) : Serializable