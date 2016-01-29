package de.derandroidpro.chromecustomtabstutorial;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openInCustomTab("https://github.com/derandroidpro");
            }
        });
    }

    private void openInCustomTab(String url){

        Uri websiteUri;
        if(!url.contains("https://") && !url.contains("http://")){
            websiteUri = Uri.parse("http://" + url);
        } else {
            websiteUri = Uri.parse(url);
        }

        CustomTabsIntent.Builder customtabintent = new CustomTabsIntent.Builder();
        customtabintent.setToolbarColor(Color.parseColor("#3F51B5"));
        customtabintent.setShowTitle(true);

        Intent copyIntent = new Intent(MainActivity.this, CopyURLBroadcast.class);
        PendingIntent copyPendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, copyIntent, 0);


        customtabintent.addMenuItem("URL kopieren", copyPendingIntent);

        if(chromeInstalled()){
            customtabintent.build().intent.setPackage("com.android.chrome");
        }

        customtabintent.build().launchUrl(MainActivity.this, websiteUri);
    }

    private boolean chromeInstalled(){
        try {
            getPackageManager().getPackageInfo("com.android.chrome", 0);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
