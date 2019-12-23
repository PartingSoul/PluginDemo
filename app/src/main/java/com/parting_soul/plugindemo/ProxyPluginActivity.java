package com.parting_soul.plugindemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parting_soul.plugin_common.ActivityInterface;

/**
 * @author parting_soul
 * @date 2019-12-21
 */
public class ProxyPluginActivity extends AppCompatActivity {
    public static final String EXTRA_CLASS_NAME = "className";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            String className = getIntent().getStringExtra(EXTRA_CLASS_NAME);
            Class clazz = getClassLoader().loadClass(className);
            ActivityInterface activityInterface = (ActivityInterface) clazz.newInstance();
            activityInterface.inject(this);
            activityInterface.onCreate(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
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

}
