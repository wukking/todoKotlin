package com.wuyson.aidllib.pool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class BinderPoolService extends Service {

    private static final String TAG = "BinderPoolService";

    private IBinder mBinderPool = new BinderPoolImpl();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: " );
        return mBinderPool;
    }
}
