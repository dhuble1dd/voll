//package com.example.voll
//
//import android.app.Notification
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.app.Service
//import android.content.Context
//import android.content.Intent
//import android.os.Binder
//import android.os.Build
//import android.os.IBinder
//import android.util.Log
//import androidx.annotation.RequiresApi
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import java.util.Random
//
//class BoundService: Service() {
//    val binder = LocalBinder()
//    override fun onBind(p0: Intent?): IBinder? {
//        return binder
//    }
//    inner class LocalBinder : Binder() {
//        // Return this instance of LocalService so clients can call public methods.
//        fun getService(): BoundService = this@BoundService
//    }
//    private val mGenerator = Random()
//    val randomNumber: Int
//        get() = mGenerator.nextInt(100)
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate() {
//        super.onCreate()
//    }
//
//    private fun createServiceNotificationChannel() {
//        if (Build.VERSION.SDK_INT < 26) {
//            return // notification channels were added in API 26
//        }
//        val channel = NotificationChannel(
//            CHANNEL_ID,
//            "Foreground Service channel",
//            NotificationManager.IMPORTANCE_DEFAULT
//        )
//        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.createNotificationChannel(channel)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        val pendingIntent: PendingIntent =
//            Intent(this, MainActivity::class.java).let { notificationIntent ->
//                PendingIntent.getActivity(this, 0, notificationIntent,
//                    PendingIntent.FLAG_IMMUTABLE)
//            }
//
//        createServiceNotificationChannel()
//
//        val notification: Notification = Notification.Builder(this, CHANNEL_ID)
//            .setContentTitle("Header")
//            .setContentText("Text")
//            .setContentIntent(pendingIntent)
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .build()
//
//// Notification ID cannot be 0.
//        startForeground(1, notification)
//
//        GlobalScope.launch {
//
//        }
//
//
//        return super.onStartCommand(intent, flags, startId)
//    }
//}