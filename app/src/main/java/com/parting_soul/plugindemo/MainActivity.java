package com.parting_soul.plugindemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.parting_soul.plugindemo.ProxyPluginActivity.EXTRA_CLASS_NAME;

public class MainActivity extends AppCompatActivity {
    public static final String PLUGIN_NAME = "function.apk";
    public static String PLUGIN_PATH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PLUGIN_PATH = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator + PLUGIN_NAME;
        outPut(PLUGIN_NAME, PLUGIN_PATH);
    }

    public void outPut(String name, String outputPath) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open(name);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            FileOutputStream fos = new FileOutputStream(outputPath);
            fos.write(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            FileUtils.closeQuietly(baos);
        }
    }

    public void onAddPlugin(View view) {
        PluginManager.getInstance().loadPlugin(this, PLUGIN_PATH);
    }

    public void onJump(View view) {
        PluginManager.getInstance().loadPlugin(this, PLUGIN_PATH);

        PackageManager packageManager = getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageArchiveInfo(PLUGIN_PATH, PackageManager.GET_ACTIVITIES);
        ActivityInfo info = packageInfo.activities[0];

        Intent intent = new Intent(this, ProxyPluginActivity.class);
        intent.putExtra(EXTRA_CLASS_NAME, info.name);
        startActivity(intent);
    }

}
