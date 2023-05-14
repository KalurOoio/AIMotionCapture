package com.mify.aimotioncapture.cameraFeature

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Point
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Size
import android.view.View
import com.google.mlkit.vision.common.PointF3D
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark
import java.util.jar.Attributes

class LandMarksView(
    context: Context,
    attributes: AttributeSet
): View(context, attributes) {
    private var viewSize = Size(0,0)
    private val mainPaint = Paint(ANTI_ALIAS_FLAG)
    private var sizeSource = Size(0, 0)
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
        var landMark = detectedPose?.getPoseLandmark(PoseLandmark.NOSE)
        if(landMark != null){
            drawLandMark(landMark, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.LEFT_EYE_INNER)
        if(landMark != null){
            drawLandMark(landMark, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.LEFT_EYE_OUTER)
        landMark?.let{
            drawLandMark(it, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.LEFT_EYE)
        landMark?.let{
            drawLandMark(it, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.RIGHT_EYE_OUTER)
        landMark?.let{
            drawLandMark(it, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.RIGHT_EYE_INNER)
        landMark?.let{
            drawLandMark(it, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.RIGHT_EYE)
        landMark?.let{
            drawLandMark(it, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.LEFT_MOUTH)
        landMark?.let{
            drawLandMark(it, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.RIGHT_MOUTH)
        landMark?.let{
            drawLandMark(it, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.LEFT_EAR)
        landMark?.let{
            drawLandMark(it, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.RIGHT_EYE)
        landMark?.let{
            drawLandMark(it, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)
        landMark?.let{
            drawLandMark(it, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
        landMark?.let{
            drawLandMark(it, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.LEFT_ELBOW)
        landMark?.let{
            drawLandMark(it, canvas)
        }
        landMark = detectedPose?.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)
        landMark?.let{
            drawLandMark(it, canvas)
        }


    }
    fun setParameters(pose: Pose, sourceSize: Size){
        detectedPose = pose
        sizeSource= sourceSize

        invalidate()
    }
    private fun drawLandMark(landMark: PoseLandmark, drawCanvas: Canvas?) {
        val position = convertPoint(landMark.position3D)
        drawCanvas?.drawCircle(position.x, position.y, 5F, mainPaint)

    }
    private fun convertPoint(targetPosition: PointF3D):PointF{
        val x1 = targetPosition.x
        val y1 = targetPosition.y
        val w1 = sizeSource.width
        val h1 = sizeSource.height
        val w2 = viewSize.width
        val h2 = viewSize.height

        val x2 = x1*w2/w1
        val y2 = y1*h2/h1
        return PointF(x2, y2)
    }



}