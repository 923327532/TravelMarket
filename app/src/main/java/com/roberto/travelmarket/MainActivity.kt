package com.roberto.travelmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.roberto.travelmarket.presentation.navigation.NavGraph
import com.roberto.travelmarket.ui.theme.TravelMarketTheme  // ⭐ IMPORTAR

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelMarketTheme {
                Surface {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}