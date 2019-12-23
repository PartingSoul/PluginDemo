package com.parting_soul.function;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.parting_soul.plugin_common.BaseActivity;

/**
 * @author parting_soul
 * @date 2019-12-23
 */
public class SecondActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_second);

        startService(new Intent(mHostActivity, PluginService.class));
    }

}
