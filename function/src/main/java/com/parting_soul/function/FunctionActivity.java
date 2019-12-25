package com.parting_soul.function;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.parting_soul.plugin_common.BaseActivity;

/**
 * @author parting_soul
 * @date 2019-12-20
 */
public class FunctionActivity extends BaseActivity {
    public static final String ACTION_BROAD = "com.parting_soul.function.plugin_broadcast";
    private BroadcastReceiver mReceiver;

    @Override
    public void inject(Activity activity) {
        super.inject(activity);
        Log.d("FunctionActivity", "inject " + activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_function);
        Toast.makeText(getApplicationContext(), "我是插件", Toast.LENGTH_SHORT).show();

        findViewById(R.id.tv_second_level)
                .setOnClickListener(v ->
                        startActivity(new Intent(mHostActivity, SecondActivity.class))
                );

        mReceiver = new PluginBroadcastReceiver();
        registerReceiver(mReceiver, new IntentFilter(ACTION_BROAD));

        //发送广播
        Intent intent = new Intent(ACTION_BROAD);
        sendBroadcast(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

}
