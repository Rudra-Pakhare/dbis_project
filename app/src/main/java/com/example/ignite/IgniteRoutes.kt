package com.example.ignite

sealed class IgniteRoutes(val route: String){
    object LoginScreen : IgniteRoutes("login_screen")
    object SignUpScreen : IgniteRoutes("signup_screen")
    object HomeScreen : IgniteRoutes("home_screen")
    object ProfileScreen : IgniteRoutes("profile_screen")
    object TrainingScreen : IgniteRoutes("training_screen")
    object ExercisesScreen : IgniteRoutes("exercises_screen")
}
