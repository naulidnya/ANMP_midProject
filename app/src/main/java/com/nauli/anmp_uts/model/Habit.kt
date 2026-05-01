package com.nauli.anmp_uts.model

data class Habit(
    val title: String,
    val description: String,
    var progress: Int,
    val target: Int,
    val unit: String,
    val icon: Int
)