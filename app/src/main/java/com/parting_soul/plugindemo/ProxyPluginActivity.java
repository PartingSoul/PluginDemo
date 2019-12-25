package com.parting_soul.plugindemo;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parting_soul.plugin_common.ActivityInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * @author parting_soul
 * @date 2019-12-21
 */
public class ProxyPluginActivity extends AppCompatActivity {
    public static final String EXTRA_CLASS_NAME = "className";
    private ActivityInterface mActivityInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            String className = getIntent().getStringExtra(EXTRA_CLASS_NAME);
            Class clazz = getClassLoader().loadClass(className);
            mActivityInterface = (ActivityInterface) clazz.newInstance();
            mActivityInterface.inject(this);
            mActivityInterface.onCreate(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mActivityInterface != null) {
            mActivityInterface.onDestroy();
        }
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }


    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        Intent newIntent = new Intent(this, ProxyPluginActivity.class);
        newIntent.putExtra(EXTRA_CLASS_NAME, intent.getComponent().getClassName());
        super.startActivity(newIntent, options);
    }

    @Override
    public ComponentName startService(Intent service) {
        Intent newIntent = new Intent(this, ProxyService.class);
        newIntent.putExtra(ProxyService.EXTRA_SERVICE_CLASSNAME, service.getComponent().getClassName());
        return super.startService(newIntent);
    }


    Map<BroadcastReceiver, ProxyBroadcastReceiver> receivers = new HashMap<>();

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        ProxyBroadcastReceiver proxyReceiver = new ProxyBroadcastReceiver(receiver);
        receivers.put(receiver, proxyReceiver);
        return super.registerReceiver(proxyReceiver, filter);
    }

    @Override
    public void unregisterReceiver(BroadcastReceiver receiver) {
        ProxyBroadcastReceiver proxyReceiver = receivers.get(receiver);

        super.unregisterReceiver(proxyReceiver);

    }

}
