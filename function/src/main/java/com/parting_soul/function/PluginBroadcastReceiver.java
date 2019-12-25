package com.parting_soul.function;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author parting_soul
 * @date 2019-12-23
 */
public class PluginBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context.getApplicationContext(), "插件收到广播", Toast.LENGTH_SHORT).show();
    }

}
