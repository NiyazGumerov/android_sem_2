//package ru.itis.homework7.auth.presentation.ui
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
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
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import ru.itis.homework7.auth.R
//import ru.itis.homework7.auth.presentation.viewmodel.AuthViewModel
//import ru.itis.homework7.auth.presentation.viewmodel.RegisterViewModel
//import ru.itis.homework7.navigation.Route
//
//
//const val USERNAME_REGEX = "^[A-Za-z0-9]+$"
//
//@Composable
//fun RegisterScreen(
//    navController: NavController,
//    registerViewModel: RegisterViewModel = viewModel()
//) {
//    val context = LocalContext.current
//    var username by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var isUsernameError by remember { mutableStateOf(false) }
//    var isPasswordError by remember { mutableStateOf(false) }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//
//    LaunchedEffect(Unit) {
//        registerViewModel.registerSharedFlow.collect {
//            if (it) {
//                navController.navigate(Route.MAIN_ROUTE.destination)
//            } else {
//                errorMessage = context.getString(R.string.user_already_exists)
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
//            label = { Text(stringResource(id = R.string.username)) })
//        Spacer(modifier = Modifier.height(8.dp))
//        OutlinedTextField(
//            value = password,
//            onValueChange = {
//                password = it
//                isPasswordError = false
//                errorMessage = null
//            },
//            label = { Text(stringResource(id = R.string.password)) },
//            visualTransformation = PasswordVisualTransformation()
//        )
//        if (errorMessage != null) {
//            Text(
//                text = errorMessage!!,
//                color = Color.Red,
//                style = MaterialTheme.typography.bodySmall,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                textAlign = TextAlign.Center
//
//            )
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = {
//            when {
//
//                username.isEmpty() -> {
//                    isUsernameError = true
//                    errorMessage = context.getString(R.string.username_is_empty)
//                }
//
//                password.isEmpty() -> {
//                    isPasswordError = true
//                    errorMessage = context.getString(R.string.password_is_empty)
//                }
//
//                !username.matches(Regex(USERNAME_REGEX)) -> {
//                    isUsernameError = true
//                    errorMessage = context.getString(R.string.wrong_username_format)
//                }
//
//                password.length < 8 -> {
//                    isPasswordError = true
//                    errorMessage = context.getString(R.string.wrong_password_format)
//                }
//
//                else -> {
//                    registerViewModel.register(
//                        username = username,
//                        password = password
//                    )
//                }
//            }
//        })
//        { Text(stringResource(id = R.string.register)) }
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(onClick = { navController.navigate(Route.AUTH_ROUTE.destination) }) {
//            Text(
//                stringResource(id = R.string.entrance)
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun RegisterScreenPreview() {
//
//        RegisterScreen(navController = rememberNavController())
//
//}