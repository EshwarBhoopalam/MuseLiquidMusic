package com.muse.liquid

import android.content.ComponentName
import android.content.Context
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.media.MediaMetadata
import android.provider.Settings
import android.content.Intent
import android.service.notification.NotificationListenerService

class MuseNotificationListener : NotificationListenerService()

object Playback {
 fun controller(ctx: Context): MediaController? = try {
  val manager = ctx.getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager
  val component = ComponentName(ctx, MuseNotificationListener::class.java)
  val sessions = manager.getActiveSessions(component)
  sessions.firstOrNull { it.packageName == "com.google.android.apps.youtube.music" }
 } catch (_: SecurityException) { null }
 fun accessGranted(ctx: Context): Boolean = Settings.Secure.getString(ctx.contentResolver, "enabled_notification_listeners")?.contains(ctx.packageName) == true
 fun requestAccess(ctx: Context) { ctx.startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)) }
 fun title(ctx: Context) = controller(ctx)?.metadata?.getString(MediaMetadata.METADATA_KEY_TITLE) ?: "Open YouTube Music"
 fun artist(ctx: Context) = controller(ctx)?.metadata?.getString(MediaMetadata.METADATA_KEY_ARTIST) ?: "MUSE Liquid Music"
 fun toggle(ctx: Context) { controller(ctx)?.let { if (it.playbackState?.state == 3) it.transportControls.pause() else it.transportControls.play() } }
 fun next(ctx: Context) { controller(ctx)?.transportControls?.skipToNext() }
 fun previous(ctx: Context) { controller(ctx)?.transportControls?.skipToPrevious() }
}
