package com.ojiofong.androidsamples

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.ojiofong.androidsamples.koin.di.allModules
import org.koin.android.ext.android.startKoin
import org.webrtc.PeerConnectionFactory

class App : Application() {

    companion object {
        private val TAG = App::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()
        initWebRTC(this)
        startKoin(this, allModules)
    }

    private fun initWebRTC(context: Context) {
        val connState = PeerConnectionFactory.initializeAndroidGlobals(context, true, true, true, null)
        Log.d(TAG, "WebRTC PeerConnectionFactory state $connState")
        Toast.makeText(context, "WebRTC Connection state $connState", Toast.LENGTH_LONG).show()
    }

    fun getAppName(): String {
        return resources.getString(R.string.app_name)
    }

}