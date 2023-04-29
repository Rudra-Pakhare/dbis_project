package com.example.ignite

sealed class IgniteRoutes(val route: String){
    object LoginScreen : IgniteRoutes("login_screen")
    object SignUpScreen : IgniteRoutes("signup_screen")
    object HomeScreen : IgniteRoutes("home_screen")
    object ProfileScreen : IgniteRoutes("profile_screen")
    object TrainingScreen : IgniteRoutes("training_screen")
    object ApiScreen : IgniteRoutes("api_screen")
    object Exercise : IgniteRoutes("exercise")
    object PostForm : IgniteRoutes("post_form")
    object SubscriptionForm : IgniteRoutes("subscription_form")
    object FeedScreen : IgniteRoutes("feed_screen")
    object UpdateProfilePic : IgniteRoutes("update_profile_screen")
    object DeletePost : IgniteRoutes("delete_post")
    object DeleteSubs : IgniteRoutes("delete_subs")
    object ChatScreen : IgniteRoutes("chat_screen")
    object MessageScreen : IgniteRoutes("message_screen")
}
