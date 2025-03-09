package com.example.mycomposeswitchwithdatastoreandroom.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycomposeswitchwithdatastoreandroom.viewmodels.SwitchViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SwitchScreen(viewModel: SwitchViewModel) {

    val states by viewModel.switchStates.collectAsState(initial = Triple(false, false, false))

    Column {
        SwitchItem(
            label = "Switch 1",
            checked = states.first,
            onCheckedChange = { viewModel.onSwitchChanged(1, it) }
        )
        SwitchItem(
            label = "Switch 2",
            checked = states.second,
            onCheckedChange = { viewModel.onSwitchChanged(2, it) }
        )
        SwitchItem(
            label = "Switch 3",
            checked = states.third,
            onCheckedChange = { viewModel.onSwitchChanged(3, it) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
                Button(
                    shape = MaterialTheme.shapes.small,
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            val switch1State = viewModel.getSwitchState(1)
                            val switch2State = viewModel.getSwitchState(2)
                            val switch3State = viewModel.getSwitchState(3)
                            Log.e("SwitchScreen", "\n")
                            Log.e("SwitchScreen", "Switch 1 State: $switch1State")
                            Log.e("SwitchScreen", "Switch 2 State: $switch2State")
                            Log.e("SwitchScreen", "Switch 3 State: $switch3State")
                        }
                    }) {
                    Text("STATUS FROM DB")
                }


            Button(
                shape = MaterialTheme.shapes.small,
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val switch1State = viewModel.getStoredSwitchState(1)
                        val switch2State = viewModel.getStoredSwitchState(2)
                        val switch3State = viewModel.getStoredSwitchState(3)
                        Log.e("SwitchScreen", "\n")
                        Log.e("SwitchScreen", "Switch 1 State: $switch1State")
                        Log.e("SwitchScreen", "Switch 2 State: $switch2State")
                        Log.e("SwitchScreen", "Switch 3 State: $switch3State")
                    }
                }) {
                Text("Status From DataStore")
            }

        } // ROW



    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //   SwitchScreen(SwitchViewModel())
}