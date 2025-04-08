package com.marcello0140.assesment01.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcello0140.assesment01.R
import com.marcello0140.assesment01.ui.theme.IdrSwapTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onAboutClick: () ->Unit = {}
) {

    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text(
                        text = stringResource(R.string.app_name).uppercase(),
                        style = TextStyle(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.tertiary
                                )
                            ),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            shadow = Shadow(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                                offset = Offset(2f, 2f),
                                blurRadius = 8f
                            )
                        ),
                        modifier = Modifier.padding(start = 4.dp)
                    )
                },

                actions = {
                    IconButton(onClick = { onAboutClick() },
                                modifier = Modifier.padding(end = 8.dp)
                    ){
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(6.dp)
                        ){
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = stringResource(R.string.tentangAplikasi),
                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },

                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),

            )
        }
    ){
        innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(
    modifier: Modifier = Modifier
){
    Text(
        text = "Aplikasi Konversi nilai Rupiah ke Mata uang Asing",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    IdrSwapTheme {
        MainScreen()
    }
}