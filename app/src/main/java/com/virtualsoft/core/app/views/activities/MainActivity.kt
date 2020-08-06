package com.virtualsoft.core.app.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.virtualsoft.core.app.R
import com.virtualsoft.core.utils.NetworkUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testConnection()
    }

    private fun testConnection() {
        val isConnected = NetworkUtils.isConnected
        Toast.makeText(this, "conectado? $isConnected", Toast.LENGTH_LONG).show()
    }
}