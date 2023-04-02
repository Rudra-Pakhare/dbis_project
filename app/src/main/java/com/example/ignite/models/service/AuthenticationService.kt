package com.example.ignite.models.service

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationService @Inject constructor(private val auth: FirebaseAuth){

    suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun createUser(email: String,password: String){
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    fun signOut() {
        auth.signOut()
    }
}
