package com.reactnativeapp;


import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class EventEmitter {
    //定义上下文对象 
    public static ReactContext myContext;

    //定义发送事件的函数
    public static void sendEvent(ReactContext reactContext, String eventName, WritableMap params) {
        //System.out.println("reactContext="+reactContext);
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName,params);
    }

    public static void send() {
        Log.v("EventEmitter", "EventEmitter.send");

        //在该方法中开启线程，并且延迟3秒，然后向JavaScript端发送事件。
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //发送事件,事件名为EventName
                WritableMap et= Arguments.createMap();
                sendEvent(myContext,"EventName",et);
            }
        }).start();
    }
}