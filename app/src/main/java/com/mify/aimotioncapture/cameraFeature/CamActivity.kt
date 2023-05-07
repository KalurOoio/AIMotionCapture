package com.mify.aimotioncapture.cameraFeature

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.Preview.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.google.common.util.concurrent.ListenableFuture
import com.mify.aimotioncapture.R
import com.mify.aimotioncapture.helpers.FrameAnalyser

@ExperimentalGetImage class CamActivity: AppCompatActivity() {
    private lateinit var cameraFuture: ListenableFuture<ProcessCameraProvider>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        cameraFuture = ProcessCameraProvider.getInstance(this)
        cameraFuture.addListener({
            val provider = cameraFuture.get()
            BindPreview(provider)
        }, ContextCompat.getMainExecutor(this))
    }

    private fun BindPreview(cameraProvider: ProcessCameraProvider) {
        val cameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
        val preview = Builder()
            .build()
        val Preview = findViewById<PreviewView>(R.id.PreviewCam)
        preview.setSurfaceProvider(Preview.surfaceProvider)
        val imageAnalyser = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
        val viewPoint = findViewById<LandMarksView>(R.id.LandMarks)
        imageAnalyser.setAnalyzer(mainExecutor, FrameAnalyser(viewPoint))

        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyser)
    }
}
