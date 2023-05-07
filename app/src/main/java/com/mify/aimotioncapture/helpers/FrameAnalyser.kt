package com.mify.aimotioncapture.helpers

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions
import com.mify.aimotioncapture.cameraFeature.LandMarksView

@ExperimentalGetImage
class FrameAnalyser(
    private val viewPoint: LandMarksView
): ImageAnalysis.Analyzer {
    private val options = PoseDetectorOptions.Builder()
        .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
        .build()
    private val detector = PoseDetection.getClient(options)
    override fun analyze(image: ImageProxy) {
        val MediaIMG = image.image
        if( MediaIMG != null){
            val imageForDetector = InputImage.fromMediaImage(MediaIMG, image.imageInfo.rotationDegrees)
            detector.process(imageForDetector)
                .addOnSuccessListener { resultPose ->
                    viewPoint.setParameters(resultPose)

                }
                .addOnFailureListener{
                    println("Detection FAILED :,(")
                }
        }
        image.close()
    }
}