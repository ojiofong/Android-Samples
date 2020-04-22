package com.ojiofong.androidsamples.notification

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ojiofong.androidsamples.R

class NotificationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                removeSelf()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_buttons, container, false)

        val button1 = rootView.findViewById<Button>(R.id.button1)
        button1.text = "Send Notification"
        button1.setOnClickListener {
            sendNotification()
        }

        val button2 = rootView.findViewById<Button>(R.id.button2)
        button2.text = "Check Notification"
        button2.setOnClickListener {
            checkNotification()
        }

        return rootView
    }

    private fun sendNotification() {
        // Create a channel and set the importance
        createNotificationChannel(CHANNEL_ID)

        // build the notification content
        val builder = NotificationCompat.Builder(context!!, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Android Samples Notification")
                .setContentText("Some content for Android Samples")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Show the notification
        NotificationManagerCompat.from(context!!)
                .notify(NOTIFICATION_ID, builder.build())
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun checkNotification() {

        // Register the channel with the system
        val notificationManager =
                context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        var displayMsg = "No Notification found"
        notificationManager.activeNotifications.forEach {
            if (it.id == NOTIFICATION_ID) {
                displayMsg = "Found Notification with id $NOTIFICATION_ID"
            }
        }

        Toast.makeText(context!!, displayMsg, Toast.LENGTH_SHORT).show()
    }

    private fun createNotificationChannel(channelId: String) {
        // Create the NotificationChannel on API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "General Channel"
            val descriptionText = "General channel for common notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager =
                    context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    // Removes this fragment
    private fun removeSelf() {
        activity!!.supportFragmentManager
                .beginTransaction()
                .remove(this)
                .commitNow()
    }

    companion object {
        const val CHANNEL_ID = "general_channel"
        const val NOTIFICATION_ID = 11

        @JvmStatic
        fun launch(fragmentManager: FragmentManager) {
            fragmentManager
                    .beginTransaction()
                    .add(android.R.id.content, NotificationFragment())
                    .commitNow()
        }
    }
}