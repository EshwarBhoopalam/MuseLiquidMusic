package com.muse.liquid

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.media.MediaMetadata
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {
    private lateinit var root: LinearLayout
    private lateinit var title: TextView
    private lateinit var artist: TextView
    private lateinit var particles: ParticleView
    private val handler = Handler(Looper.getMainLooper())
    private val refresh = object : Runnable {
        override fun run() {
            updateTrack()
            handler.postDelayed(this, 1500)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.BLACK
        window.navigationBarColor = Color.BLACK
        root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 36, 24, 24)
            setBackgroundColor(Color.rgb(5, 9, 18))
        }
        setContentView(root)
        text("MUSE  /  LIQUID MUSIC", 18, Color.rgb(125, 220, 255))
        text("YouTube Music companion", 13, Color.LTGRAY)
        title = text("Waiting for music", 25, Color.WHITE)
        artist = text("", 15, Color.LTGRAY)
        particles = ParticleView(this)
        root.addView(particles, LinearLayout.LayoutParams(-1, 170))
        val controls = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        root.addView(controls)
        button(controls, "☷ Queue") { openMusic() }
        button(controls, "◀") { Playback.previous(this) }
        button(controls, "⏯") { Playback.toggle(this) }
        button(controls, "▶") { Playback.next(this) }
        button(controls, "♡ Like") { openMusic() }
        text("LYRICS  •  synchronized source not connected", 14, Color.LTGRAY)
        text("Connect an authorized timestamped lyrics provider to enable live lyrics and translation.", 14, Color.GRAY)
        button(root, "☾  Lyrics AOD  •  ON") { startActivity(Intent(this, LyricsActivity::class.java)) }
        button(root, "Visualizer  •  Particle Flow  |  Glow 35%") {
            Toast.makeText(this, "Particle Flow selected", Toast.LENGTH_SHORT).show()
        }
        button(root, "Grant playback access") { Playback.requestAccess(this) }
        if (!Playback.accessGranted(this)) {
            Toast.makeText(this, "Enable MUSE notification access to read YouTube Music playback", Toast.LENGTH_LONG).show()
        }
    }

    private fun text(value: String, size: Int, color: Int): TextView = TextView(this).apply {
        text = value
        textSize = size.toFloat()
        setTextColor(color)
        setPadding(4, 14, 4, 14)
        root.addView(this)
    }

    private fun button(parent: LinearLayout, label: String, action: () -> Unit) {
        Button(this).apply {
            text = label
            textSize = 12f
            setAllCaps(false)
            setOnClickListener { action() }
            val params = if (parent.orientation == LinearLayout.HORIZONTAL) {
                LinearLayout.LayoutParams(0, -2, 1f)
            } else {
                LinearLayout.LayoutParams(-1, -2)
            }
            parent.addView(this, params)
        }
    }

    private fun openMusic() {
        try {
            packageManager.getLaunchIntentForPackage("com.google.android.apps.youtube.music")?.let {
                startActivity(it)
            } ?: Toast.makeText(this, "YouTube Music not installed", Toast.LENGTH_SHORT).show()
        } catch (_: Exception) { }
    }

    private fun updateTrack() {
        title.text = Playback.title(this)
        artist.text = Playback.artist(this)
        val controller = Playback.controller(this)
        val artwork = controller?.metadata?.getBitmap(MediaMetadata.METADATA_KEY_ALBUM_ART)
            ?: controller?.metadata?.getBitmap(MediaMetadata.METADATA_KEY_ART)
        particles.accent = Palette.fromArtwork(artwork)
        particles.invalidate()
    }

    override fun onResume() {
        super.onResume()
        handler.post(refresh)
    }

    override fun onPause() {
        handler.removeCallbacks(refresh)
        super.onPause()
    }
}
