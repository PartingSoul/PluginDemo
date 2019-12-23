package com.parting_soul.function;

import android.content.Intent;
import android.util.Log;

import com.parting_soul.plugin_common.BaseService;

/**
 * @author parting_soul
 * @date 2019-12-23
 */
public class PluginService extends BaseService {
    public static final String TAG = PluginService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "run: 插件service执行中");
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

}
