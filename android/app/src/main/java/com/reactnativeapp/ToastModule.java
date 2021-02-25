package com.reactnativeapp;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;
import java.util.HashMap;

import javax.annotation.Nonnull;

import static android.content.Context.MODE_PRIVATE;

public class ToastModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY ="LONG";

    ToastModule(ReactApplicationContext context){
        super(context);
        reactContext = context;
    }

    // define react-native module name
    @Nonnull
    @Override
    public String getName() {
        return "ToastExample";
    }

    // returns constant values to javascript
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        return constants;
    }

    @ReactMethod
    public void show(String message, int duration){
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

    @ReactMethod
    public void logoutWidget(){
        Log.v("ToastModule", "logoutWidget_triggered");
        Intent logoutWidget = new Intent(reactContext, ClickIntentService.class);
        logoutWidget.setAction(ClickIntentService.ACTION_LOGOUT);
        reactContext.startService(logoutWidget);
    }

    @ReactMethod
    public void loginWidget(){
        Intent loginIntent = new Intent(reactContext, ClickIntentService.class);
        loginIntent.setAction(ClickIntentService.ACTION_LOGIN);
        reactContext.startService(loginIntent);
    }
}
