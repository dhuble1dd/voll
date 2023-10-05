package com.example.voll

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import java.util.Random


class MyService : Service() {
    private val mBinder: IBinder = MyBinder()

    private val mGenerator = Random()

    inner class MyBinder : Binder() {
        val service: MyService
            get() =
                this@MyService
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= 26) {
            val CHANNEL_ID = "my_channel_01"
            val channel = NotificationChannel(
                CHANNEL_ID,
                "My Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("A service is running in the background")
                .setContentText("").build()
            startForeground(1, notification)
        }
    }
    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }

    val randomNumber: Int
        get() = mGenerator.nextInt(100)
}
