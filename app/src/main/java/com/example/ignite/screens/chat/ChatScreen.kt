package com.example.ignite.screens.chat

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ignite.IgniteRoutes
import com.example.ignite.IgniteState
import com.example.ignite.composables.bottombar.MyBottomBar
import com.example.ignite.models.User
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme

@Composable fun ChatScreen(
    appState : IgniteState,
    viewModel: ChatViewModel,
) {
    val user = viewModel.accountService.currentUser.collectAsState(initial = User())
    Scaffold (
        bottomBar = { MyBottomBar(appState = appState,3,user.value.isAnonymous) },
    ) { _ ->
        ChatTheme(
            isInDarkMode = false
        ) {
            ChannelsScreen(
                title = "Ignite Chat",
                isShowingHeader = true,
                onItemClick = {
                    appState.navigate(IgniteRoutes.MessageScreen.route + "/${it.cid}")
                },
            )
        }
    }
}