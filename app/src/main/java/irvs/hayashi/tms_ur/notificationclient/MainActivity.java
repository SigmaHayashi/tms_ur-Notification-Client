package irvs.hayashi.tms_ur.notificationclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        FirebaseMessaging.getInstance().subscribeToTopic("tms_ur_notification")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String message = "Success to Subscribe tms_ur_notification";
                        if(!task.isSuccessful()){
                            message = "Failed to Subscribe tms_ur_notification";
                        }
                        Log.d(TAG, message);
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });

        Intent intent = getIntent();
        /*
        String title = intent.getStringExtra("message.notification.title");
        String body = intent.getStringExtra("message.notification.body");
        Log.d(TAG, "Title : " + title);
        Log.d(TAG, "Body : " + body);
        Log.d(TAG, "Intent : " + intent.getStringExtra("message"));
        //Log.d(TAG, intent.getExtras().getString("message.notification.title"));
        */
        if(intent.getExtras() != null){
            Log.d(TAG, intent.getExtras().toString());
            Log.d(TAG, "Title : " + intent.getExtras().getString("notification.title"));
            Log.d(TAG, "Body : " + intent.getExtras().getString("notification.body"));
            Log.d(TAG, "Start App : " + intent.getExtras().getString("start_app"));
            String start_app = intent.getExtras().getString("start_app");
            if(start_app != null && start_app.equals("Smart Previewed Reality")){
                Intent new_intent = new Intent(MainActivity.this, StartSmartPreviewedReality.class);
                startActivity(new_intent);

                finish();
            }
        }

        Button button_start_SPR = findViewById(R.id.button_start_SPR);
        button_start_SPR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                String package_name = "irvs.hayashi.smartpreviewedreality";
                Intent intent = getPackageManager().getLaunchIntentForPackage(package_name);
                startActivity(intent);
                */
                //StartSmartPreviewedReality();
                Intent intent = new Intent(MainActivity.this, StartSmartPreviewedReality.class);
                startActivity(intent);

                finish();
            }
        });
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "tms_ur_notification";
            String description = "Notification from ROS-TMS";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("tms_ur_notification", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /*
    public void StartSmartPreviewedReality() {
        String package_name = "irvs.hayashi.smartpreviewedreality";
        Intent intent = getPackageManager().getLaunchIntentForPackage(package_name);
        startActivity(intent);
    }
    */
}
