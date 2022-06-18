package com.example.pruebawear;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pruebawear.databinding.ActivityMainBinding;

public class MainActivity extends Activity {
    private Button wBoton = null;
    private ActivityMainBinding binding;
    private Intent intent;
    private Intent callIntent;
    private PendingIntent pendingIntent, callPendingIntent;

    private NotificationCompat.Builder notification;
    private NotificationManagerCompat nm;
    private NotificationCompat.WearableExtender wearableExtender;
    private NotificationCompat.Action action;

    String idChannel = "Mi_Canal";
    public static int idNotification = 001;

    private NotificationCompat.BigTextStyle bigTextStyle;

    String longText = "Without BigStyle, only a single line of text would be visible" +
            "Any additional text would not appear directly in the notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wBoton = findViewById(R.id.wBoton);

        intent = new Intent(
                MainActivity.this,
                MainActivity.class
        );

        nm = NotificationManagerCompat.from(MainActivity.this);

        wearableExtender = new NotificationCompat.WearableExtender();

        bigTextStyle = new NotificationCompat.BigTextStyle().bigText(longText);

        wBoton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                callIntent = new Intent(
                        MainActivity.this,
                        CallActivity.class);
                callIntent.setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                |Intent.FLAG_ACTIVITY_CLEAR_TASK);

                PendingIntent DialPendingIntent = PendingIntent.getActivity(
                        MainActivity.this,
                        0,
                        callIntent,
                        PendingIntent.FLAG_ONE_SHOT);

                int importance = NotificationManager.IMPORTANCE_HIGH;
                String name = "Notification";

                NotificationChannel notificationChannel = new NotificationChannel(
                        idChannel,
                        name,
                        importance);

                nm.createNotificationChannel(notificationChannel);

                pendingIntent = PendingIntent.getActivity(
                        MainActivity.this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Buzón de voz")
                        .setContentText("Carlos Soto dejo un mensaje en el buzón")
                        .setContentIntent(pendingIntent)
                        .addAction(R.drawable.llamada, "Llamar", DialPendingIntent)
                        .setAutoCancel(true);

                nm.notify(idNotification, notification.build());






                /* notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notificación Wear")
                        .setContentText(longText)
                        .setContentIntent(pendingIntent)
                        .extend(wearableExtender)
                        .setVibrate(new long[]{10,200,300,400,500,400,300,200,400})
                        .setStyle(bigTextStyle);


                nm.notify(idNotification, notification.build());*/


                /* PAGINAS
                List<Notification> pages = new ArrayList<Notification>();

                for(int i =1; i<=3; i++){
                    Notification nt = new NotificationCompat.Builder(MainActivity.this, idChannel)
                            .setContentTitle("Pagina " + i)
                            .setContentText("Texto de la pagina " + i)
                            .build();

                    pages.add(nt);
                }

                NotificationCompat.WearableExtender extender = new NotificationCompat.
                        WearableExtender().addPages(pages);

                Notification notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notificación multipágina")
                        .setContentText("Esta es la primera página")
                        .extend(extender)
                        .build();

                nm.notify(idNotification, notification); */


                /*Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notification2 = new NotificationCompat.Builder(MainActivity.this, idChannel)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Notificación Wear")
                                .setContentText("Mi primera notificación Wear")
                                .extend(wearableExtender)
                                .build();

                        nm.notify(idNotification, notification2);
                    }
                    }, 5000); */
            }
        });
    }
}