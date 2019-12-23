package com.parting_soul.plugin_common;

import android.app.Service;
import android.content.Intent;

/**
 * @author parting_soul
 * @date 2019-12-23
 */
public interface ServiceInterface {

    void inject(Service service);

    void onCreate();

    int onStartCommand(Intent intent, int flags, int startId);

    void onDestroy();
}
