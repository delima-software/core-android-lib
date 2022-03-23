package com.virtualsoft.core.app.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.virtualsoft.core.app.R
import com.virtualsoft.core.utils.NetworkUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        testConnection()
    }

    private fun testConnection() {
        val isConnected = NetworkUtils.checkNetworkConnection(this)
        Toast.makeText(this, "connected? $isConnected", Toast.LENGTH_LONG).show()
    }
}