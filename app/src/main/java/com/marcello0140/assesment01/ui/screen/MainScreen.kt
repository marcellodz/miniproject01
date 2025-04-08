package com.marcello0140.assesment01.ui.screen

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.marcello0140.assesment01.R
import com.marcello0140.assesment01.ui.theme.IdrSwapTheme
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.marcello0140.assesment01.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController
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
                    IconButton(onClick = { navController.navigate(Screen.About.route) },
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
        ScreenContent(Modifier.padding(innerPadding).padding(16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(
    modifier: Modifier = Modifier
){
    val currencyList = listOf("USD", "EUR", "JPY", "SG")
    val currencyFlagMap = mapOf(
        "USD" to R.drawable.us,
        "EUR" to R.drawable.eu,
        "JPY" to R.drawable.jp,
        "SG" to R.drawable.sg
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedCurrency by remember { mutableStateOf(currencyList[0]) }

    var nominalRaw by remember { mutableStateOf("") }

    val nominalValue = nominalRaw.toLongOrNull() ?: 0L

    var hasilKonversi by remember { mutableStateOf<Double?>(null) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedCurrency,
                onValueChange = { },
                readOnly = true,
                label = {Text(stringResource(R.string.mataUangTujuan))},
                leadingIcon = {
                    currencyFlagMap[selectedCurrency]?.let {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = selectedCurrency,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )

            val scrollState = rememberScrollState()



            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.heightIn(max = 150.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 150.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(scrollState)
                            .fillMaxWidth()
                            .padding(end = 12.dp) // biar gak ketiban scrollbar
                    ) {
                        currencyList.forEach { currency ->
                            DropdownMenuItem(
                                text = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        currencyFlagMap[currency]?.let {
                                            Image(
                                                painter = painterResource(id = it),
                                                contentDescription = currency,
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                        Text(
                                            text = currency,
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                    }
                                },
                                onClick = {
                                    selectedCurrency = currency
                                    expanded = false
                                }
                            )
                        }
                    }

                    //scrollbar nya
                    if (scrollState.maxValue > 0) {
                        val scrollRatio = scrollState.value.toFloat() / scrollState.maxValue.toFloat()
                        val indicatorHeightRatio = 150f / (currencyList.size * 50f)
                        val indicatorHeight = indicatorHeightRatio * 150f

                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(top = scrollRatio * (150f - indicatorHeight).coerceAtLeast(0f).dp)
                                .width(4.dp)
                                .height(indicatorHeight.dp)
                                .background(Color.Gray, RoundedCornerShape(50))
                        )
                    }
                }
            }
        }

        TextField(
            value = nominalRaw,
            onValueChange = { newValue ->
                nominalRaw = newValue.filter { it.isDigit() }
            },
            label = { Text (stringResource(R.string.input)) },
            leadingIcon = {
                Text(stringResource(R.string.rp), style =  MaterialTheme.typography.headlineMedium)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                hasilKonversi = countKurs(nominalValue, selectedCurrency)
            },
            enabled = nominalValue > 0,
            modifier = Modifier.fillMaxWidth()
                            .padding(top = 8.dp)
        ) {
            Text (stringResource(R.string.hitung))
        }

        hasilKonversi?.let {
            Text(
                text = stringResource(R.string.hasilKonversi).format(it, selectedCurrency),
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Button(
                onClick = {
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "Hasil konversi dari Rp $nominalValue adalah %.2f %s".format(it, selectedCurrency)
                        )
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Bagikan hasil konversi via"))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.bagikan))
            }

        }

    }

}

private fun countKurs(nominal: Long, mataUang: String): Double {
    val rate = when(mataUang){
        "USD" ->  0.00005934
        "EUR" -> 0.00005399
        "JPY" -> 0.00874
        "SG"  -> 0.00007999
        else -> 0.0
    }
    return nominal * rate
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    IdrSwapTheme {
        MainScreen(rememberNavController())
    }
}