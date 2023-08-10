package com.example.notification;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity {
    //private  static final String CHANNEL_ID = "My Channel";
//private static final int NOTIFICATION_ID = 100;
    //   private static final String CUSTOM_INTENT = "course.example.BroadcastReceivers.show_toast";
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = findViewById(R.id.btn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NotificationChannel channel = new NotificationChannel("myId", "MyName",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyUser("New Code Added");
            }
        });

//        Button button = (Button) findViewById(R.id.btn);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v){
//                sendBroadcast(new Intent(CUSTOM_INTENT), Manifest.permission.VIBRATE);
//            }
//        });

//
//        Notification notification;
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//             notification = new Notification.Builder(this)
//                    .setContentText("New message")
//                    .setSubText("New message from Jio")
//                     .setChannelId(CHANNEL_ID)
//                    .build();
//
//            nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
//        } else {
//             notification = new Notification.Builder(this)
//                    .setContentText("New message")
//                    .setSubText("New message from Jio")
//                    .build();
//
//        }
//
//nm.notify(NOTIFICATION_ID, notification);
    }

    private void notifyUser(String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel")
                .setContentTitle("New Message for You")
                .setContentText(message)
                .setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(
                    this,
                    new String []  {Manifest.permission.POST_NOTIFICATIONS},
                    1
            );
            return;
        }
        managerCompat.notify(null, 0, builder.build());



    }
}
