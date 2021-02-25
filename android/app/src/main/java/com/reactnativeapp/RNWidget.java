package com.reactnativeapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;


/**
 * Implementation of App Widget functionality.
 */
public class RNWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // get config
        String currentType = context.getSharedPreferences("widget_type", Context.MODE_PRIVATE).getString("type", "logout");


        // toggle different layout
        int usedLayout = currentType == "login"? R.layout.r_n_widget: R.layout.r_n_widget_logout;
        RemoteViews views = new RemoteViews(context.getPackageName(), usedLayout);

        Log.v("RNWidget", "currentType: "+currentType);

        if(currentType == "login"){
            CharSequence widgetText = context.getString(R.string.appwidget_text);
            // Construct the RemoteViews object
            views.setTextViewText(R.id.appwidget_text, widgetText);

            // setup navigation intent
            Intent openAppIntent = new Intent(context, MainActivity.class);
            Intent emitEventIntent = new Intent(context, ClickIntentService.class);
            emitEventIntent.setAction(ClickIntentService.ACTION_FROM_WIDGET);
            Intent[] intents = {emitEventIntent,openAppIntent };
            PendingIntent pendingIntent = PendingIntent.getActivities(context, 0, intents, 0);
            views.setOnClickPendingIntent(R.id.open_app_button, pendingIntent);
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}