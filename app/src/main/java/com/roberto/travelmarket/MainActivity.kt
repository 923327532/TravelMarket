package com.roberto.travelmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.roberto.travelmarket.presentation.navigation.NavGraph
import com.roberto.travelmarket.ui.theme.TravelMarketTheme
import com.roberto.travelmarket.viewmodel.FavoritosViewModel

class MainActivity : ComponentActivity() {
    private val favoritosViewModel: FavoritosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelMarketTheme {
                Surface {
                    val navController = rememberNavController()
                    NavGraph(
                        navController = navController,
                        favoritosViewModel = favoritosViewModel
                    )
                }
            }
        }
    }
}