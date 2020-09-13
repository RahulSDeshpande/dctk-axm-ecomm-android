package com.rahulografy.axmecomm.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

private const val TAG = "NetworkConnection"

fun isNetworkAvailable(context: Context?): Boolean {
    val service = Context.CONNECTIVITY_SERVICE
    val manager = context?.getSystemService(service) as ConnectivityManager?
    val network = manager?.activeNetworkInfo
    Log.d(TAG, "hasNetworkAvailable: ${(network != null)}")
    return (network?.isConnected) ?: false
}

fun isServerUp(context: Context): Boolean {
    if (isNetworkAvailable(context)) {
        try {
            val connection =
                URL(Constants.Network.Api.URL_BASE).openConnection() as HttpURLConnection
            connection.setRequestProperty("User-Agent", "Test")
            connection.setRequestProperty("Connection", "close")
            connection.connectTimeout = 10000
            connection.connect()
            Log.d(TAG, "isServerUp(): ${(connection.responseCode == 200)}")
            return (connection.responseCode == 200)
        } catch (e: IOException) {
            Log.e(TAG, "Error checking internet connection", e)
        }
    } else {
        Log.w(TAG, "Server is unavailable!")
    }
    Log.d(TAG, "isServerUp(): false")
    return false
}