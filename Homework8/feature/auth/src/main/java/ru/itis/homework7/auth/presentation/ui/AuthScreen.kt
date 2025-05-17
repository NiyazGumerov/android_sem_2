//package ru.itis.homework7.auth.presentation.ui
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import ru.itis.homework7.auth.R
//import ru.itis.homework7.auth.presentation.viewmodel.AuthViewModel
//import ru.itis.homework7.navigation.Route
//
//
//@Composable
//fun AuthScreen(
//    navController: NavController,
//    authViewModel: AuthViewModel = viewModel()
//) {
//    val context = LocalContext.current
//    var username by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var isUsernameError by remember { mutableStateOf(false) }
//    var isPasswordError by remember { mutableStateOf(false) }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//
//    LaunchedEffect(Unit) {
//        authViewModel.authSharedFlow.collect {
//            if (it) {
//                navController.navigate(Route.MAIN_ROUTE.destination)
//            } else {
//                errorMessage = context.getString(R.string.incorrect_login_or_password)
//            }
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        OutlinedTextField(
//            value = username,
//            onValueChange = {
//                username = it
//                isUsernameError = false
//                errorMessage = null
//            },
//            label = { Text(stringResource(R.string.username)) },
//            isError = isUsernameError
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        OutlinedTextField(
//            value = password,
//            onValueChange = {
//                password = it
//                isPasswordError = false
//                errorMessage = null
//            },
//            label = { Text(stringResource(R.string.password)) },
//            isError = isPasswordError,
//            visualTransformation = PasswordVisualTransformation()
//        )
//        if (errorMessage != null) {
//            Text(
//                text = errorMessage!!,
//                color = Color.Red,
//                style = MaterialTheme.typography.bodySmall
//            )
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(
//            onClick =
//                {
//                    when {
//                        username.isEmpty() -> {
//                            isUsernameError = true
//                            errorMessage = context.getString(R.string.username_is_empty)
//
//                        }
//
//                        password.isEmpty() -> {
//                            isPasswordError = true
//                            errorMessage = context.getString(R.string.password_is_empty)
//                        }
//
//                        else -> {
//                            authViewModel.auth(username, password)
//                        }
//                    }
//                }
//        ) { Text(stringResource(id = R.string.login)) }
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(onClick = { navController.navigate(Route.REGISTER_ROUTE.destination) }) {
//            Text(
//                stringResource(id = R.string.registration)
//            )
//        }
//    }
//}
//
