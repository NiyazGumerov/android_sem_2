package ru.itis.homework9.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itis.homework9.R

@Composable
fun GraphScreen() {
    val context = LocalContext.current

    var pointsCount by remember { mutableIntStateOf(0) }
    val pointsValues = remember { mutableStateListOf<Int>() }

    var pointsCountText by remember { mutableStateOf("") }
    var pointsValuesText by remember { mutableStateOf("") }

    var isPointsCountTextFieldError by remember { mutableStateOf(false) }
    var isPointsValuesTextFieldError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = pointsCountText,
            onValueChange = {
                pointsCountText = it
                errorMessage = null
                isPointsCountTextFieldError = false
            },
            label = { Text(stringResource(R.string.points_count)) },
            isError = isPointsCountTextFieldError
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = pointsValuesText,
            onValueChange = {
                pointsValuesText = it
                errorMessage = null
                isPointsValuesTextFieldError = false
            },
            label = { Text(stringResource(R.string.points_values_by_commas)) },
            isError = isPointsValuesTextFieldError
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick =
                {
                    when {
                        pointsCountText.isBlank() -> {
                            isPointsCountTextFieldError = true
                            errorMessage = context.getString(R.string.points_count_is_empty)
                        }

                        pointsValuesText.isBlank() -> {
                            isPointsValuesTextFieldError = true
                            errorMessage = context.getString(R.string.points_values_is_empty)
                        }

                        pointsCountText.toIntOrNull() == null -> {
                            isPointsCountTextFieldError = true
                            errorMessage = context.getString(R.string.points_count_is_not_int)
                        }

                        pointsCountText.toInt() <= 1 -> {
                            isPointsCountTextFieldError = true
                            errorMessage = context.getString(R.string.points_count_is_negative)
                        }

                        pointsValuesText.split(",").any { it.trim().toIntOrNull() == null }
                                || pointsValuesText.split(",").any { it.trim().toInt() < 0 } -> {
                            isPointsValuesTextFieldError = true
                            errorMessage = context.getString(R.string.points_values_is_not_int)
                        }

                        pointsCountText.toInt() !=
                                pointsValuesText.split(",").size -> {
                            isPointsValuesTextFieldError = true
                            errorMessage = context.getString(R.string.points_count_is_not_equal)
                        }

                        else -> {
                            pointsCount = pointsCountText.toInt()
                            pointsValues.clear()
                            pointsValues.addAll(
                                pointsValuesText.split(",").map { it.trim().toInt() })
                        }
                    }
                }
        ) { Text(stringResource(R.string.generate_graph)) }

        if (pointsCount > 0 && pointsValues.isNotEmpty()) {
            GraphItem(pointsCount, pointsValues)
        }
    }
}