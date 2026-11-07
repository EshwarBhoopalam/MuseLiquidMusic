package com.muse.liquid

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView

/** Dedicated OLED-black foreground mode; not a replacement for Samsung system AOD. */
class LyricsActivity: Activity() {
 override fun onCreate(savedInstanceState:Bundle?) { super.onCreate(savedInstanceState)
  window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
  window.attributes=window.attributes.apply { screenBrightness=.035f }
  window.decorView.systemUiVisibility=5894 or 1024 or 512
  val root=LinearLayout(this).apply { orientation=1; gravity=Gravity.CENTER; setBackgroundColor(Color.BLACK); setPadding(30,20,30,20) }
  setContentView(root)
  fun line(s:String,size:Float,color:Int) { root.addView(TextView(this).apply { text=s; textSize=size; setTextColor(color); gravity=Gravity.CENTER; setPadding(0,20,0,20) }) }
  line("MUSE  •  LYRICS AOD",14f,Color.GRAY)
  line("♪",40f,Color.rgb(90,200,235))
  line("GRAFFITI LYRICS",32f,Color.WHITE)
  line("Synchronized lyrics will appear here when a licensed timestamped source is connected.",16f,Color.LTGRAY)
  line("Tap Back to return to the player",12f,Color.GRAY)
 }
}
