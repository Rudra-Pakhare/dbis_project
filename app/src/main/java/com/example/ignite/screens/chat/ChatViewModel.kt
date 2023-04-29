package com.example.ignite.screens.chat

import androidx.lifecycle.ViewModel
import com.example.ignite.IgniteViewModel
import com.example.ignite.models.service.AccountService
import com.example.ignite.models.service.LogService
import com.example.ignite.models.service.impl.AccountServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    val accountService: AccountService,
    private val client: ChatClient,
    logService: LogService,
) : IgniteViewModel(logService)