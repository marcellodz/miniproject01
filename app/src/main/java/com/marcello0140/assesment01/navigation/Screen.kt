package com.marcello0140.assesment01.navigation

sealed class Screen(val route: String){
    object Home : Screen("mainScreen")
    object About : Screen("aboutScreen")
}