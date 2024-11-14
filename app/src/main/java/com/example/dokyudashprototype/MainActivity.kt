package com.example.dokyudashprototype

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "example_channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Adjusting system window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        // BOTTOM NAV BUTTONS
        val home: Button = findViewById(R.id.home_button)
        home.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, HomeFragment()).addToBackStack(null).commit()
        }

//        val scanqr: Button = findViewById(R.id.scanqr_button)
//        scanqr.setOnClickListener {
//            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, QrFragment()).addToBackStack(null).commit()
//        }

        val notification: Button = findViewById(R.id.notif_button)
        notification.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, NotificationFragment()).addToBackStack(null).commit()
        }
        val profile: Button = findViewById(R.id.profile_button)
        profile.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ProfileFragment()).addToBackStack(null).commit()
        }
        // Check and request notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1)
            } else {
                // Schedule the notification after permission is already granted
//                scheduleNotification()
            }
        } else {
            // Schedule the notification for older Android versions
//            scheduleNotification()
        }
    }

//    private fun scheduleNotification() {
//        // Schedule a notification to show after 10 seconds
//        Handler().postDelayed({
//            showNotification()
//        }, 1000 ) // 10 seconds = 10000 milliseconds
//    }

//    private fun showNotification() {
//        // Create the notification
//        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.logo) // Replace with your app's icon
//            .setContentTitle("PSA: Review Complete!")
//            .setContentText("Your documents have been successfully reviewed by the PSA. Please proceed with the next steps.")
//            .setPriority(NotificationCompat.PRIORITY_HIGH) // Use PRIORITY_HIGH to show as a pop-up
//            .setCategory(NotificationCompat.CATEGORY_MESSAGE) // Set the category
//            .setAutoCancel(true) // Dismiss the notification when clicked
//            .setColor(Color.BLUE) // Optional: Customize the notification color
//
//        // Create the NotificationManager
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        // Create the NotificationChannel for Android 8.0 and above
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "Example Channel"
//            val descriptionText = "Channel for example notifications"
//            val importance = NotificationManager.IMPORTANCE_HIGH // Set to IMPORTANCE_HIGH for pop-up notifications
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        // Show the notification
//        notificationManager.notify(NOTIFICATION_ID, builder.build())
//    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 1) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, schedule the notification
//                scheduleNotification()
//            } else {
//                Log.d("MainActivity", "Notification permission denied.")
//            }
//        }


}
