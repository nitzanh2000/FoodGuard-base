package com.example.foodguard.data.user;

import android.provider.ContactsContract.CommonDataKinds.Email

data class UserDTO(
    val email: String = "",
    val displayName: String = "",
    val password : String,
    val phone : String,
    val profilePicture : String? = "",
    val id: String? = null
) {
    fun toUserModel(): UserModel {
        return UserModel(
            id = id ?: "",
            email = email,
            display_name = displayName,
            password = password,
            phone = phone,
            profile_picture = profilePicture
        )
    }
}