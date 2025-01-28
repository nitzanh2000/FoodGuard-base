package com.example.foodguard.data.user;
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.auth.FirebaseAuth

@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey val id: String = "",
    val email: String,
    val display_name: String,
    val password : String,
    val phone : String,
    val profile_picture: String?
) {
    fun toUserDto() : UserDTO {
        return UserDTO(
            id = id,
            email = email,
            display_name = display_name,
            password = password,
            phone = phone,
            profile_picture = profile_picture
        )
    }
    companion object {
        fun fromFirebaseAuth(): UserModel {
            val user = FirebaseAuth.getInstance().currentUser

            return UserModel(
                id = user?.uid!!,
                email = user.email!!,
                display_name = user.displayName!!,
                password = "",
                phone = user.phoneNumber!!,
                profile_picture = ""
            )
        }
    }
}
