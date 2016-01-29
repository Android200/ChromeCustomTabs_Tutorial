package de.derandroidpro.chromecustomtabstutorial;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CopyURLBroadcast extends BroadcastReceiver {
    public CopyURLBroadcast() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
      String url = intent.getDataString();
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(ClipData.newPlainText("URL", url));
        Toast.makeText(context , "URL in die Zwischenablage kopiert.", Toast.LENGTH_SHORT).show();
    }
}
