package ru.itis.homework7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import dagger.hilt.android.AndroidEntryPoint
import ru.itis.homework7.presentation.ui.RatesScreen
import ru.itis.homework7.ui.theme.Homework7Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework7Theme {
                Scaffold { padding ->
                    RatesScreen()
                }
            }
        }
    }
}

