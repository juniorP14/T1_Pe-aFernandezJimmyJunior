package com.example.t1_peafernandezjimmyjunior

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.t1_peafernandezjimmyjunior.ui.screens.PantallaCarrito
import com.example.t1_peafernandezjimmyjunior.ui.theme.T1_PeñaFernandezJimmyJuniorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            T1_PeñaFernandezJimmyJuniorTheme {
                PantallaCarrito()
            }
        }
    }
}