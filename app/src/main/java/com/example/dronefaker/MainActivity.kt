package com.example.dronefaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    private val bluetoothManager = BluetoothManager(this)
    private val wifiManager = WifiManager(this)
    private val droneSignalGenerator = DroneSignalGenerator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DroneFakerApp()
        }
    }

    @Composable
    fun DroneFakerApp() {
        MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.TopCenter)
                    ) {
                        Button(onClick = { startFakingSignals() }) {
                            Text("Start Faking Signals")
                        }
                        Button(onClick = { stopFakingSignals() }) {
                            Text("Stop Faking Signals")
                        }
                    }
                }
            }
        }
    }

    private fun startFakingSignals() {
        val remoteIDSignal = droneSignalGenerator.generateRemoteIDSignal()
        bluetoothManager.broadcastSignal(remoteIDSignal)
        wifiManager.broadcastSignal(remoteIDSignal)
    }

    private fun stopFakingSignals() {
        bluetoothManager.stopBroadcasting()
        wifiManager.stopBroadcasting()
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        DroneFakerApp()
    }
}