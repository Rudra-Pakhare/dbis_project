package com.example.ignite.models.service

import com.example.ignite.models.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean
    val currentUserToken: String
    val currentUser: Flow<User>
    var loggedIn: Boolean
    var firstLaunch : Boolean

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun createAnonymousAccount()
    suspend fun deleteAccount()
    suspend fun signOut()
    suspend fun signIn(name:String, email: String, password: String)
}