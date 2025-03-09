package com.example.mycomposeswitchwithdatastoreandroom.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycomposeswitchwithdatastoreandroom.ui.screens.SwitchItem
import com.example.mycomposeswitchwithdatastoreandroom.ui.screens.SwitchScreen
import com.example.mycomposeswitchwithdatastoreandroom.ui.theme.MyappTheme
import com.example.mycomposeswitchwithdatastoreandroom.viewmodels.SwitchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SwitchViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        SwitchScreen(viewModel)
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyappTheme {
        Column {
            SwitchItem(label = "Switch Ayar 1", checked = false, onCheckedChange = {})
            SwitchItem(label = "Switch Ayar 2", checked = true,  onCheckedChange = {})
            SwitchItem(label = "Switch Ayar 3", checked = false, onCheckedChange = {})
        }
    }
}