package com.example.androidplayground.anotherDaggerHiltDemo.model

data class DisplayList(val items: List<DisplayData>)
data class DisplayData(
    val id: Int,
    val name: String?,
    val description: String?,
    val owner: Owner?
)

data class Owner(val avatar_url: String?)