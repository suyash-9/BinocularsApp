package com.example.binoculars.facedetect

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions

class FaceDetectAnalyzer(private val onSuccessListener : (String) -> Unit,
                         private val onErrorListener : (Exception) -> Unit) : ImageAnalysis.Analyzer {

    val detector = FaceDetection.getClient(
        FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
            .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()
    )

    @SuppressLint("UnsafeExperimentalUsageError", "UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {

        Log.d("FACEDETECT", "image analysed")

        imageProxy.image?.let {
            val inputImage = InputImage.fromMediaImage(
                it,
                imageProxy.imageInfo.rotationDegrees
            )

            detector.process(inputImage)
                .addOnSuccessListener { faces ->
                    var result=""
                    Log.d("FACDETECT", "Faces = ${faces.size}")

                    faces.forEach {
                        Log.d("FACEDETECT", """
                            leftEye ${it.leftEyeOpenProbability}
                            rightEye ${it.rightEyeOpenProbability}
                            smile ${it.smilingProbability}
                    """.trimIndent())
                        result+=" FACEDETECT\n leftEye ${it.leftEyeOpenProbability}  rightEye ${it.rightEyeOpenProbability}  smile ${it.smilingProbability} "
                    }
                    onSuccessListener(result)
                }
                .addOnFailureListener { ex ->
                    onErrorListener(ex)
                    Log.e("FACEDETECT", "Detection failed", ex)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }

        } ?: imageProxy.close() // close if image not found either

    }
}