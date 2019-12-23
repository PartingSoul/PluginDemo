package com.parting_soul.plugin_common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author parting_soul
 * @date 2019-12-20
 */
@SuppressLint("MissingSuperCall")
public class BaseActivity extends AppCompatActivity implements ActivityInterface {
    protected Activity mHostActivity;

    @Override
    public void inject(Activity activity) {
        mHostActivity = activity;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mHostActivity.setContentView(layoutResID);
    }

    @Override
    public void setContentView(View view) {
        mHostActivity.setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mHostActivity.setContentView(view, params);
    }

    @Override
    public Context getApplicationContext() {
        return mHostActivity.getApplicationContext();
    }

    @Override
    public void startActivity(Intent intent) {
        this.startActivity(intent, null);
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        mHostActivity.startActivity(intent, options);
    }

    @Override
    public ComponentName startService(Intent service) {
        return mHostActivity.startService(service);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return mHostActivity.findViewById(id);
    }
}
