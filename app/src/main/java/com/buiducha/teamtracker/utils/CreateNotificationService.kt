package com.buiducha.teamtracker.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.activity.MainActivity
import com.buiducha.teamtracker.data.model.Notification
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


class CreateNotificationService : Service() {


    private lateinit var databaseRef: DatabaseReference
    private lateinit var intent: Intent
    private lateinit var pendingIntent: PendingIntent
    private lateinit var notificationManager: NotificationManager
    private val notificationId = 1
    private lateinit var channel: NotificationChannel


    override fun onCreate() {
        super.onCreate()
        intent = Intent(this, MainActivity::class.java)
        val requestCode = 1
        val pendingIntentFlag = PendingIntent.FLAG_IMMUTABLE
        pendingIntent = PendingIntent.getActivity(this, requestCode, intent, pendingIntentFlag)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel Name" // Thay đổi tên kênh thông báo của bạn ở đây
            val importance = NotificationManager.IMPORTANCE_DEFAULT // Đặt mức độ quan trọng phù hợp
            channel = NotificationChannel("channel_id", name, importance).apply {
                description = "My Channel Description" // Thay đổi mô tả kênh thông báo của bạn ở đây
            }
            notificationManager.createNotificationChannel(channel)
        }
        Log.d("cong", "onCreate service")
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val auth: FirebaseAuth = Firebase.auth
        databaseRef = FirebaseDatabase.getInstance().getReference("notification")
        databaseRef.orderByChild("receiverId").equalTo(auth.currentUser?.uid)
            .addChildEventListener(object : ChildEventListener {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val notification = snapshot.getValue(Notification::class.java)
                    if (notification != null) {
                        createNotification(notification)
                    }
                    Log.d("cong", "onChildAdded service")
                }


                @RequiresApi(Build.VERSION_CODES.O)
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    val notification = snapshot.getValue(Notification::class.java)
                    if (notification != null) {
                        createNotification(notification)
                    }
                    Log.d("cong", "onChildChanged service")
                }


                override fun onChildRemoved(snapshot: DataSnapshot) {
                    // Handle child removed
                }


                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                    // Handle child moved
                }


                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        Log.d("cong", "onStartCommand")


        return START_STICKY
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotification(notification: Notification) {
        val noti = NotificationCompat.Builder(this, "channel_id")
            .setContentTitle("Thông báo từ TeamTracker")
            .setContentText(notification.content)
            .setSmallIcon(R.drawable.teamtracker2)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(notificationId, noti)
        Log.d("cong", "create Noti")
    }
}
