package com.muse.liquid

import android.graphics.Bitmap
import android.graphics.Color
import kotlin.math.*

object Palette {
 fun fromArtwork(bitmap: Bitmap?): Int {
  if (bitmap == null) return Color.rgb(94, 199, 239)
  val pixels = IntArray(3)
  val samples = 0.coerceAtLeast(1)
  var best = Color.rgb(94,199,239)
  var bestScore = -1f
  for (y in 0 until bitmap.height step max(1,bitmap.height/24)) for (x in 0 until bitmap.width step max(1,bitmap.width/24)) {
   val c=bitmap.getPixel(x,y); val hsv=FloatArray(3); Color.colorToHSV(c,hsv)
   val score=hsv[1]*(1f-abs(hsv[2]-.62f))
   if (score>bestScore && hsv[2]>.18f) { best=c; bestScore=score }
  }
  val hsv=FloatArray(3); Color.colorToHSV(best,hsv); hsv[1]=hsv[1].coerceIn(.35f,.78f); hsv[2]=hsv[2].coerceIn(.70f,.95f)
  return Color.HSVToColor(hsv)
 }
}
