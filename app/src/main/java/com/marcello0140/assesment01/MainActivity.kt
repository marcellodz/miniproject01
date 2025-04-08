package com.marcello0140.assesment01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.marcello0140.assesment01.navigation.SetupNavGraph
import com.marcello0140.assesment01.ui.theme.IdrSwapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IdrSwapTheme {
                SetupNavGraph()
            }
        }
    }
}