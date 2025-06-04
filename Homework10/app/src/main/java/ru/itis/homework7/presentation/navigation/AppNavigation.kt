package ru.itis.homework7.presentation.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.itis.homework7.NavigationEventBus
import ru.itis.homework7.R
import ru.itis.homework7.auth.data.datastore.USER_ID_KEY
import ru.itis.homework7.auth.presentation.ui.AuthScreen
import ru.itis.homework7.auth.presentation.ui.RegisterScreen
import ru.itis.homework7.navigation.Route
import ru.itis.homework7.presentation.ui.ForFirebaseScreen
import ru.itis.homework7.presentation.ui.RatesScreen

@Composable
fun AppNavigation(
    startDestination: Boolean
) {
    val navController = rememberNavController()
    val context = LocalContext.current

    val toastText = stringResource(id = R.string.text_for_toast)
    LaunchedEffect(Unit) {
        NavigationEventBus.events.collect { route ->
            if (navController.currentBackStackEntry?.destination?.route != route.destination) {
                navController.navigate(route.destination)
            } else {
                Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
            }
        }
    }
    NavHost(navController, if (startDestination) Route.MAIN_ROUTE.destination else Route.AUTH_ROUTE.destination) {
        composable(Route.AUTH_ROUTE.destination) { AuthScreen(navController) }
        composable(Route.REGISTER_ROUTE.destination) { RegisterScreen(navController) }
        composable(Route.MAIN_ROUTE.destination) { RatesScreen(navController) }
        composable(Route.FOR_FIREBASE_ROUTE.destination) { ForFirebaseScreen() }
    }

}
