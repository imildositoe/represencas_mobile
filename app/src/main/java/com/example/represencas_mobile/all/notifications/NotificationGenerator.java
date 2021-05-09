package com.example.represencas_mobile.all.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.represencas_mobile.MainActivity;
import com.example.represencas_mobile.R;


public class NotificationGenerator {

    private static final int NOTIFICATION_ID_OPEN_ACTIVITY = 9;

    public static void openActivityNotification(Context context, String titulo, String mensagem) {
        NotificationCompat.Builder nc = new NotificationCompat.Builder(context);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        nc.setContentIntent(pendingIntent);
        nc.setAutoCancel(true);
        nc.setSmallIcon(R.drawable.ic_fingerprint_black_24dp);
        nc.setContentTitle(titulo);
        nc.setContentText(mensagem);
        nm.notify(NOTIFICATION_ID_OPEN_ACTIVITY, nc.build());
    }
}
