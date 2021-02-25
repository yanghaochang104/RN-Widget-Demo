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

public class EventEmitterModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;

    public EventEmitterModule(ReactApplicationContext reactContext){
        super(reactContext);
        EventEmitter.myContext = reactContext;
    }


    // define react-native module name
    @Nonnull
    @Override
    public String getName() {
        return "EventEmitterExample";
    }

    // returns constant values to javascript
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }
}
