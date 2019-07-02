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
import android.widget.Toast;

public class MainActivity extends Activity {
    //tushar
    View mail,crm,inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        mail = findViewById(R.id.mail);
        crm = findViewById(R.id.crm);
        inventory = findViewById(R.id.inventory);

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchPackage("com.zoho.mail&hl=en_IN");
            }
        });

        crm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchPackage("com.zoho.crm&hl=en_IN");
            }
        });

        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Coming Soon!",
                        Toast.LENGTH_LONG).show();            }
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
