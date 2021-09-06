package com.example.binoculars.textrecognition

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.lang.Exception

class TextAnalyzer(
    private val onSuccessListener : (String) -> Unit,
    private val onErrorListener : (Exception) -> Unit
) : ImageAnalysis.Analyzer {

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)


    @SuppressLint("UnsafeExperimentalUsageError", "UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        Log.d("TEXT", "image analysed")

        imageProxy.image?.let {
            val inputImage = InputImage.fromMediaImage(
                it,
                imageProxy.imageInfo.rotationDegrees
            )

            recognizer.process(inputImage)
                .addOnSuccessListener { text ->
                    var result = ""
                    text.textBlocks.forEach { block ->
                        Log.d(
                            "TEXT", """
                            LINES = ${block.lines.joinToString("\n") { it.text }}
                        """.trimIndent()
                        )
                        result += block.text
                    }
                    onSuccessListener(result)
                }
                .addOnFailureListener { ex ->
                    onErrorListener(ex)
                    Log.e("TEXT", "Detection failed", ex)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }

        } ?: imageProxy.close() // close if image not found either

    }
}