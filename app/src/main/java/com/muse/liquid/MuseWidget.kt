package com.muse.liquid

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.RemoteViews

class MuseWidget: AppWidgetProvider() {
 override fun onUpdate(context:Context, manager:AppWidgetManager, ids:IntArray) {
  for (id in ids) {
   val rv=RemoteViews(context.packageName,android.R.layout.simple_list_item_1)
   rv.setTextViewText(android.R.id.text1,"♫  MUSE LIQUID MUSIC\n${Playback.title(context)}\nTap to open player")
   rv.setTextColor(android.R.id.text1,Color.rgb(115,210,245))
   val pi=PendingIntent.getActivity(context,0,Intent(context,MainActivity::class.java),PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
   rv.setOnClickPendingIntent(android.R.id.text1,pi)
   manager.updateAppWidget(id,rv)
  }
 }
}
