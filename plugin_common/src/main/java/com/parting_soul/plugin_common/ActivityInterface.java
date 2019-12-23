package com.parting_soul.plugin_common;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author parting_soul
 * @date 2019-12-21
 */
public interface ActivityInterface {

    void inject(Activity activity);

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

}
