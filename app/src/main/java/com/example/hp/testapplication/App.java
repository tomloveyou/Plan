package com.example.hp.testapplication;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.standards.library.app.AppContext;
import com.standards.library.app.ReturnCode;
import com.standards.library.app.ReturnCodeConfig;
import com.standards.library.network.NetworkConfig;

/**
 * @author yl
 * @version 1.0
 * @date 2017/12/6 11:38
 */
public class App  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        AppContext.getInstance().init(getApplicationContext());
        NetworkConfig.setBaseUrl(BuildConfig.HOST_URL);
        ReturnCodeConfig.getInstance().initReturnCode(ReturnCode.CODE_SUCCESS, ReturnCode.CODE_EMPTY);
    }
}
