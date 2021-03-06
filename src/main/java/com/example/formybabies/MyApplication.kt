package com.example.formybabies

import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kakao.sdk.common.KakaoSdk

//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.ktx.Firebase
//import com.google.firebase.storage.FirebaseStorage
//import com.google.firebase.storage.ktx.storage
//import com.kakao.sdk.common.KakaoSdk

class MyApplication: MultiDexApplication() {
    companion object{
        lateinit var auth: FirebaseAuth
        var email:String? = null

//        lateinit var db : FirebaseFirestore
//        lateinit var storage : FirebaseStorage

        fun checkAuth(): Boolean{
            var currentUser = auth.currentUser
            return currentUser?.let{
                email = currentUser.email
                currentUser.isEmailVerified
            }?: let{
                false
            }
        }
    }
//
    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth
        KakaoSdk.init(this, "cf3d09334ccd077e36db8e8e55a7a613")

//        db = FirebaseFirestore.getInstance()
//        storage = Firebase.storage

    }
}