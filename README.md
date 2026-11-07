# MUSE Liquid Music — Android starter (v0.1)

Native Kotlin Android companion for YouTube Music playback controls and an OLED-black lyrics-mode shell. This is an early starter project, not a finished music player.

## Included

- YouTube Music media-session discovery through Android Notification Listener access.
- Play/pause, previous/next controls where the active media session supports them.
- Track and artist metadata, plus an album-art-derived accent color.
- A lightweight Particle Flow visualizer (decorative; not audio-reactive).
- A foreground, OLED-black lyrics screen. It is not a replacement for Samsung's system AOD.
- A basic home-screen widget that opens the player.

## Known limitations

Synchronized lyrics, translation, true audio-reactive capture, persistent settings, multiple visualizer styles, interactive widget controls, and a full liquid-glass UI are not implemented yet. Queue and Like open YouTube Music rather than claiming unsupported integration.

## Build and install

The GitHub Actions workflow builds a debug APK on pushes to `main` and can also be started manually from **Actions → Build MUSE Liquid Music APK → Run workflow**. It uses GitHub-hosted Linux runners, Android SDK 35, JDK 17, and Gradle 8.9 without paid build services.

Download the `MUSE-Liquid-Music-debug-APK` artifact from a successful run and extract `app-debug.apk`. Install it on Android 10 or later. Open MUSE, enable **Notification access** for MUSE in Android Settings, start YouTube Music, then return to MUSE.

The debug APK is for testing and is not a Play Store release. Android may ask you to allow installation from the app used to open the APK.
