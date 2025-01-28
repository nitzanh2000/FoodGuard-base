package com.example.foodguard.data.post

import java.util.Date

data class PostDTO(
    val description: String = "",
    val serving: Int = 0,
    val image: String,
    val address: String,
    val authorId: String,
    val expirationDate: String,
    val id: String? = null
) {
    fun toPostModel(): PostModel {
        return PostModel(
            id = id ?: "",
            description = description,
            author_id = authorId,
            image = image,
            address = address,
            serving =  serving,
            expiration_date = expirationDate
        )
    }
}