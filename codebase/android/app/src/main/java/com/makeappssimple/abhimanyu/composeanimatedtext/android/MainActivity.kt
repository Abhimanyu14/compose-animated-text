package com.makeappssimple.abhimanyu.composeanimatedtext.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.makeappssimple.abhimanyu.composeanimatedtext.android.ui.theme.ComposeAnimatedTextTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimatedTextTheme {
                AnimatedText()
            }
        }
    }
}
