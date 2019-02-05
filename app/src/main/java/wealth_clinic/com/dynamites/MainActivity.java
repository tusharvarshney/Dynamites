package wealth_clinic.com.dynamites;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button button,docButton,driveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        button = findViewById(R.id.button);
        docButton = findViewById(R.id.docs_button);
        driveButton = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchPackage("com.zoho.crm");
            }
        });

        docButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchPackage("com.google.android.apps.docs.editors.sheets");
            }
        });

        driveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchPackage("com.google.android.apps.docs");
            }
        });
    }


   void launchPackage(String appPackageName){
       boolean isAppInstalled = appInstalledOrNot(appPackageName);
       if(isAppInstalled) {

           Intent LaunchIntent = getPackageManager()
                   .getLaunchIntentForPackage(appPackageName);
           startActivity(LaunchIntent);
       } else {
           try {
               startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
           } catch (android.content.ActivityNotFoundException anfe) {
               startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
           }                }
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
}
