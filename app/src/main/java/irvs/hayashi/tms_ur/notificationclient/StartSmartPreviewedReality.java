package irvs.hayashi.tms_ur.notificationclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartSmartPreviewedReality extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        startSmartPreviewedReality();

        finish();
    }

    void startSmartPreviewedReality() {
        String package_name = "irvs.hayashi.smartpreviewedreality";
        Intent intent = getPackageManager().getLaunchIntentForPackage(package_name);
        startActivity(intent);
    }
}
