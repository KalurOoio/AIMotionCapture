package com.mify.aimotioncapture.cameraFeature

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.util.Size
import android.view.View
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark
import java.util.jar.Attributes

class LandMarksView(
    context: Context,
    attributes: AttributeSet
): View(context, attributes) {
    private var viewSize = Size(0,0)
    private val mainPaint = Paint(ANTI_ALIAS_FLAG)
    init {
        mainPaint.color = Color.MAGENTA
        mainPaint.strokeWidth = 10.0F
        mainPaint.style = Paint.Style.FILL
    }
    private var detectedPose: Pose? = null
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewSize = Size(w, h)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val landMark = detectedPose?.getPoseLandmark(PoseLandmark.NOSE)
        canvas?.drawCircle(
            (viewSize.width/2).toFloat(),
            (viewSize.height/2).toFloat(),
            40F,
            mainPaint

        )
    }
    fun setParameters(pose: Pose){
        detectedPose = pose
        invalidate()
    }



}