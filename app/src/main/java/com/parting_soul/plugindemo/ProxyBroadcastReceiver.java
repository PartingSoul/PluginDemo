package com.parting_soul.plugindemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @author parting_soul
 * @date 2019-12-23
 */
public class ProxyBroadcastReceiver extends BroadcastReceiver {
    private BroadcastReceiver targetBroadcastReceiver;

    public ProxyBroadcastReceiver(BroadcastReceiver receiver) {
        this.targetBroadcastReceiver = receiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("ProxyBroadcastReceiver", "onReceive: ");
        targetBroadcastReceiver.onReceive(context, intent);
    }

}
