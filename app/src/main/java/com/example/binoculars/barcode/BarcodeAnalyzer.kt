package com.example.binoculars.barcode

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.binoculars.BottomSheetFragment
import com.example.binoculars.R
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.barcode.BarcodeScanning



class BarcodeAnalyzer(private val onSuccessListener : (String) -> Unit,
                      private val onErrorListener : (Exception) -> Unit) : ImageAnalysis.Analyzer {


    var scanner = BarcodeScanning.getClient()

    @SuppressLint("UnsafeExperimentalUsageError", "UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        Log.d("BARCODE", "image analysed")

        imageProxy.image?.let {
            var inputImage = InputImage.fromMediaImage(
                it,
                imageProxy.imageInfo.rotationDegrees
            )

            scanner.process(inputImage)
                .addOnSuccessListener { codes ->
                    var result=""
                    codes.forEach { barcode ->




                        Log.d(
                            "BARCODE", """
                            Format = ${barcode.format}
                            Value = ${barcode.rawValue}
                        """.trimIndent()
                        )

                        result+=" Format = ${barcode.format}  Value = ${barcode.rawValue}"
                        

                    }
                    onSuccessListener(result)


                }
                .addOnFailureListener { ex ->
                    onErrorListener(ex)
                    Log.e("BARCODE", "Detection failed", ex)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }

        } ?: imageProxy.close() // close if image not found either


    }

}

