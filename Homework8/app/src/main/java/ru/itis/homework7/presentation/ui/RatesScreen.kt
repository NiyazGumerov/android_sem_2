package ru.itis.homework7.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.itis.homework7.R
import ru.itis.homework7.domain.model.Rates
import ru.itis.homework7.presentation.model.RatesState
import ru.itis.homework7.presentation.viewmodel.RatesViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private const val BASE_LOCALE = "ru"
private const val DATE_FORMAT = "yyyy-MM-dd"

@Composable
fun RatesScreen(
    ratesViewModel: RatesViewModel = viewModel()
) {
    val ratesState by ratesViewModel.ratesState.collectAsState()

    var showDialog by remember { mutableStateOf(false) }
    val calendar = Calendar.getInstance()
    val formatter = SimpleDateFormat(DATE_FORMAT, Locale(BASE_LOCALE))
    var date by remember { mutableStateOf(calendar.time) }
    val formattedDate = remember { derivedStateOf { formatter.format(date) } }

    val messageForNetwork = stringResource(R.string.data_got_from_network)
    val messageForCache = stringResource(R.string.data_got_from_cache)

    val context = LocalContext.current

    LaunchedEffect(Unit){
        ratesViewModel.sourceSharedFlow.collect {
            val message = when (it){
                Rates.Source.NETWORK -> messageForNetwork
                Rates.Source.CACHE -> messageForCache
                null -> null
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
    Column {
        when (ratesState) {
            RatesState.Init -> {

            }

            RatesState.Error -> {
                Toast.makeText(
                    LocalContext.current,
                    stringResource(R.string.error),
                    Toast.LENGTH_SHORT
                ).show()
            }

            RatesState.Loading -> {
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 28.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    repeat(8) {
                        ShimmerAnimation(80.dp)
                    }
                }
            }

            is RatesState.Success -> {
                val rates = (ratesState as RatesState.Success).rates.rates
                Text(
                    text = formattedDate.value,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    textAlign = TextAlign.Center,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.CenterHorizontally
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            showDialog = true
                        }
                    ) {
                        Text(stringResource(R.string.button_another_date))
                    }
                    Button(
                        onClick = {
                            date = calendar.time
                            ratesViewModel.getActualCourse()
                        }
                    ) {
                        Text(stringResource(R.string.button_actual_course))
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    rates.let {
                        if (it != null) {
                            items(it.entries.toList()) { item ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                        .padding(vertical = 16.dp, horizontal = 12.dp)
                                ) {
                                    Text(
                                        text = item.key,
                                        fontSize = 20.sp,
                                    )
                                    Text(
                                        text = item.value.toString(),
                                        fontSize = 12.sp,
                                        color = Color.DarkGray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        if (showDialog) {
            DatePickerModal(
                { pickedDate ->
                    if (pickedDate != null) {
                        val calendarDate = Date(pickedDate)
                        date = calendarDate
                        val calendarFormattedDate =
                            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(calendarDate)
                        ratesViewModel.getCourseByDateUseCase(calendarFormattedDate)
                        showDialog = false
                    }
                },
                { showDialog = false }
            )
        }
    }
}