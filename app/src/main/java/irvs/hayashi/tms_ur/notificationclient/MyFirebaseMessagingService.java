package irvs.hayashi.tms_ur.notificationclient;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        /*
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }
        */

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Title : " + remoteMessage.getNotification().getTitle());
            Log.d(TAG, "Body : " + remoteMessage.getNotification().getBody());
            Log.d(TAG, "Start App : " + remoteMessage.getData().get("start_app"));
        }

        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), remoteMessage.getData().get("start_app"));

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    public void showNotification(String title, String message, @Nullable String start_app) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "tms_ur_notification")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.notification_icon)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        if(start_app != null && start_app.equals("Smart Previewed Reality")){
            Intent result_intent = new Intent(this, StartSmartPreviewedReality.class);
            TaskStackBuilder stack_builder = TaskStackBuilder.create(this);
            stack_builder.addNextIntentWithParentStack(result_intent);
            PendingIntent result_pending_intent = stack_builder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(result_pending_intent);
        }
        else {
            Intent result_intent = new Intent(this, MainActivity.class);
            TaskStackBuilder stack_builder = TaskStackBuilder.create(this);
            stack_builder.addNextIntentWithParentStack(result_intent);
            PendingIntent result_pending_intent = stack_builder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(result_pending_intent);
        }
        /*
        Intent result_intent = new Intent(this, StartSmartPreviewedReality.class);
        TaskStackBuilder stack_builder = TaskStackBuilder.create(this);
        stack_builder.addNextIntentWithParentStack(result_intent);
        PendingIntent result_pending_intent = stack_builder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(result_pending_intent);
        */

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(999, builder.build());

        Log.d(TAG, "showNotification");
    }
}
