package com.parting_soul.plugindemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.parting_soul.plugin_common.ServiceInterface;

/**
 * @author parting_soul
 * @date 2019-12-23
 */
public class ProxyService extends Service {
    public static final String EXTRA_SERVICE_CLASSNAME = "className";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getClassLoader();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String className = intent.getStringExtra(EXTRA_SERVICE_CLASSNAME);
        try {
            Class clazz = getClassLoader().loadClass(className);
            ServiceInterface serviceInterface = (ServiceInterface) clazz.newInstance();
            return serviceInterface.onStartCommand(intent, flags, startId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
