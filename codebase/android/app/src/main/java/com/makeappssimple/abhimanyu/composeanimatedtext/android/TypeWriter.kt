package com.makeappssimple.abhimanyu.composeanimatedtext.android

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlin.streams.toList
import kotlinx.coroutines.delay

@Composable
fun TypewriterTextSample() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xFFEFEFEF),
            ),
    ) {
        TypewriterText(
            texts = listOf(
                "Hello Android! 💜",
                "Hello Developers! 👋"
            ),
        )
    }
}

@Composable
fun TypewriterText(
    texts: List<String>,
) {
    var textIndex by remember {
        mutableStateOf(0)
    }
    var textToDisplay by remember {
        mutableStateOf("")
    }
    val textCharsList: List<List<String>> = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            texts.map {
                it.splitToCodePoints()
            }
        } else {
            texts.map { text ->
                text.map {
                    it.toString()
                }
            }
        }
    }

    LaunchedEffect(
        key1 = texts,
    ) {
        while (textIndex < textCharsList.size) {
            textCharsList[textIndex].forEachIndexed { charIndex, _ ->
                textToDisplay = textCharsList[textIndex]
                    .take(
                        n = charIndex + 1,
                    ).joinToString(
                        separator = "",
                    )
                delay(160)
            }
            textIndex = (textIndex + 1) % texts.size
            delay(1000)
        }
    }

    Text(
        text = textToDisplay,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    )
}

@RequiresApi(Build.VERSION_CODES.N)
fun String.splitToCodePoints(): List<String> {
    return codePoints()
        .toList()
        .map {
            String(Character.toChars(it))
        }
}
