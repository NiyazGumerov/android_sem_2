package ru.itis.homework7.navigation

enum class Route(val destination: String){
    AUTH_ROUTE("auth"),
    REGISTER_ROUTE("register"),
    MAIN_ROUTE("main"),
    FOR_FIREBASE_ROUTE("for_firebase")
}