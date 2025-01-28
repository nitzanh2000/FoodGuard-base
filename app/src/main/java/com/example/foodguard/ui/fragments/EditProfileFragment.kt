package com.example.foodguard.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.foodguard.R
import com.example.foodguard.data.PostViewModel
import com.example.foodguard.data.user.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditProfileFragment  : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }
    private val viewModel: PostViewModel by activityViewModels()

    private lateinit var imageView: ImageView
    private var userId = "/0yJw5Nkp2dIPt51GSP9G"
    private var mainUser: UserModel? = null

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val usernameText = view.findViewById<TextView>(R.id.display_name_input)
        imageView = view.findViewById<ImageView>(R.id.profile_image)

        viewModel.getAllPostsByUserId(userId).observe(viewLifecycleOwner) { posts ->
            Log.w("nitzan_test_connection", posts.toString());
        }


        viewModel.getUserById(userId).observe(viewLifecycleOwner) { user ->
            Log.w("nitzan_test_connection", user.toString());

            user?.let {
                mainUser = it
                usernameText.text = it.display_name ?: "Guest"

                if(it.profile_picture != "") {
                    //imageView.setImageBitmap(decodeBase64ToImage(it.profile_picture ?: ""))
                } else {
                    val googleImage = FirebaseAuth.getInstance().currentUser?.photoUrl
                    if(googleImage !== null) {
                        Glide.with(this) // 'this' can be a Fragment or Activity context
                            .load(googleImage)
                            .into(imageView)
                        imageView.setImageURI(googleImage)
                    }
                }




//                imageView.setOnClickListener {
//                    pickImageFromGallery()
//                }
            } ?: run {
                // Handle user not found
                usernameText.text = "Guest"
            }
        }

    }

}