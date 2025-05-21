package ru.itis.homework7.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.itis.homework7.auth.data.datastore.USER_ID_KEY
import ru.itis.homework7.navigation.Route
import ru.itis.homework7.presentation.ui.RatesScreen

@Composable
fun AppNavigation(
    startDestination: Boolean
) {
    RatesScreen()
//    val navController = rememberNavController()
//    NavHost(navController, if (startDestination) Route.MAIN_ROUTE.destination else Route.MAIN_ROUTE.destination) {
////        composable(Route.AUTH_ROUTE.destination) { AuthScreen(navController) }
////        composable(Route.REGISTER_ROUTE.destination) { RegisterScreen(navController) }
//        composable(Route.MAIN_ROUTE.destination) { RatesScreen() }
//    }
}
