package com.example.mohitekacareassignment.presentation.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mohitekacareassignment.presentation.core.theme.MohitEkaCareAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MohitEkaCareAssignmentTheme {
                MainScreen()
            }
        }
    }
}