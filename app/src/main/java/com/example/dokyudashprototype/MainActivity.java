package com.example.dokyudashprototype;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import androidx.core.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "example_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // BOTTOM NAV BUTTONS
        Button home = findViewById(R.id.home_button);
        home.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new HomeFragment()).addToBackStack(null).commit();
        });

        Button scanqr = findViewById(R.id.scanqr_button);
        scanqr.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new QrFragment()).addToBackStack(null).commit();
        });


        Button profile = findViewById(R.id.profile_button);
        profile.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, new ProfileFragment()).addToBackStack(null).commit();
        });

        // Check and request notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
            } else {
                // Schedule the notification after permission is already granted
                scheduleNotification();
            }
        } else {
            // Schedule the notification for older Android versions
            scheduleNotification();
        }
    }

    private void scheduleNotification() {
        // Schedule a notification to show after 10 seconds
        new Handler().postDelayed(this::showNotification, 1000); // 10 seconds = 10000 milliseconds
    }

    private void showNotification() {
        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo) // Replace with your app's icon
                .setContentTitle("PSA: Review Complete!")
                .setContentText("Your documents have been successfully reviewed by the PSA. Please proceed with the next steps.")
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Use PRIORITY_HIGH to show as a pop-up
                .setCategory(NotificationCompat.CATEGORY_MESSAGE) // Set the category
                .setAutoCancel(true) // Dismiss the notification when clicked
                .setColor(Color.BLUE); // Optional: Customize the notification color

        // Create the NotificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Create the NotificationChannel for Android 8.0 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Example Channel";
            String descriptionText = "Channel for example notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH; // Set to IMPORTANCE_HIGH for pop-up notifications
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(descriptionText);
            notificationManager.createNotificationChannel(channel);
        }

        // Show the notification
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, schedule the notification
                scheduleNotification();
            } else {
                Toast.makeText(this, "Notification permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
