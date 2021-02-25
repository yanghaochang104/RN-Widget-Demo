package com.reactnativeapp;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class ClickIntentService extends IntentService {
    public static final String ACTION_LOGIN = "com.reactnativeapp.login";
    public static final String ACTION_LOGOUT = "com.reactnativeapp.logout";
    public static final String ACTION_FROM_WIDGET = "com.reactnativeapp.from_widget";


    public ClickIntentService() {
        super("ClickIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            Log.v("ClickIntentService", action);
            if (ACTION_LOGIN.equals(action)) {
                handleType("login");
            }
            if(ACTION_LOGOUT.equals(action)){
                handleType("logout");
            }
            if(ACTION_FROM_WIDGET.equals(action)){
                EventEmitter.send();
            }
        }
    }

    private void handleType(String targetType) {
        Log.v("ClickIntentService", "handleType: "+targetType);
        // change stored data(flag)
        getSharedPreferences("widget_type", MODE_PRIVATE)
                .edit()
                .putString("type", targetType)
                .apply();

        // get widget
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] widgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RNWidget.class));
        // update widget view
        for (int appWidgetId : widgetIds) {
            RNWidget.updateAppWidget(getApplicationContext(), appWidgetManager, appWidgetId);
        }
    }
}