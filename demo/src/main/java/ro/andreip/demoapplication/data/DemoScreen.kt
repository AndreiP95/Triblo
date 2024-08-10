package data

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


/*
 * Demo Screen
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DemoScreen() {
    Scaffold(
        content = { _ ->
            Text("Demo text for architectural pattern")
        }
    )
}