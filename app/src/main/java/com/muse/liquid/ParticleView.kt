package com.muse.liquid

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import kotlin.math.*

class ParticleView(ctx: Context): View(ctx) {
 var accent:Int=Color.CYAN
 var animateParticles=true
 private val paint=Paint(3)
 private var tick=0f
 override fun onDraw(canvas: Canvas) {
  super.onDraw(canvas)
  val w=width.toFloat(); val h=height.toFloat()
  for (i in 0 until 105) {
   val u=i/104f
   val x=u*w
   val y=h*.5f + sin(u*16f+tick)*h*.19f + cos(u*31f-tick*.45f)*h*.075f
   paint.color=accent; paint.alpha=(45+(i%7)*13).coerceAtMost(135); paint.style=Paint.Style.FILL
   canvas.drawCircle(x,y+sin(i*1.9f+tick)*h*.08f,1.3f+(i%4)*.55f,paint)
  }
  if (animateParticles && isShown) { tick+=.025f; postInvalidateDelayed(40) }
 }
}
