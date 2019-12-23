package com.parting_soul.plugindemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @author parting_soul
 * @date 2019-12-21
 */
public class PluginManager {
    private static PluginManager sPluginManager;
    private DexClassLoader mClassLoader;
    private Resources mResources;

    private PluginManager() {
    }

    public static PluginManager getInstance() {
        if (sPluginManager == null) {
            synchronized (PluginManager.class) {
                if (sPluginManager == null) {
                    sPluginManager = new PluginManager();
                }
            }
        }
        return sPluginManager;
    }

    public void loadPlugin(Context context, String path) {
        try {
            File apkFile = new File(path);

            File cacheFile = context.getDir("classloader", Context.MODE_PRIVATE);
            mClassLoader = new DexClassLoader(apkFile.getAbsolutePath(), cacheFile.getAbsolutePath()
                    , null, context.getClassLoader());


            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager, path);

            mResources = new Resources(assetManager, context.getResources().getDisplayMetrics(),
                    context.getResources().getConfiguration());

            Log.d("plugin==>", "插件加载成功");
        } catch (Exception e) {
            Log.d("plugin==>", "插件加载失败");

        }

    }


    public ClassLoader getClassLoader() {
        return mClassLoader;
    }

    public Resources getResources() {
        return mResources;
    }
}
