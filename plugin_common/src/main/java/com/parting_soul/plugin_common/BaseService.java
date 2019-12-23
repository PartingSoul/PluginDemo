package com.parting_soul.plugin_common;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author parting_soul
 * @date 2019-12-23
 */
public class BaseService extends Service implements ServiceInterface {
    protected Service mHostService;

    @Override
    public void inject(Service service) {
        this.mHostService = service;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
