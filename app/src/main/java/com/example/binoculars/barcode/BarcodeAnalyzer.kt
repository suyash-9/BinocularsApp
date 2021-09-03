package com.example.binoculars.barcode

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.binoculars.BottomSheetFragment
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.barcode.BarcodeScanning



class BarcodeAnalyzer() : ImageAnalysis.Analyzer {


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
                    codes.forEach { barcode ->
                        //val strin="Format = ${barcode.format}  Value = ${barcode.rawValue}"
                        //str+="Format = ${barcode.format}  Value = ${barcode.rawValue}"


                        var banalyzer= Bundle()
                        banalyzer.putString("Analyzer_key","Format = ${barcode.format}  Value = ${barcode.rawValue}")

                        var bottomSheetFragment = BottomSheetFragment()
                        bottomSheetFragment.setArguments(banalyzer)
                        Log.d(
                            "BARCODE", """
                            Format = ${barcode.format}
                            Value = ${barcode.rawValue}
                        """.trimIndent()
                        )
//                        val strin="Format = ${barcode.format}  Value = ${barcode.rawValue}"
//                        //str+="Format = ${barcode.format}  Value = ${barcode.rawValue}"
//
//                        val banalyzer= Bundle()
//                        banalyzer.putString("Analyzer_key","Format = ${barcode.format}  Value = ${barcode.rawValue}")
//
//                        val bottomSheetFragment = BottomSheetFragment()
//                        bottomSheetFragment.setArguments(banalyzer)
                        
                        





                    }


                }
                .addOnFailureListener { ex ->
                    Log.e("BARCODE", "Detection failed", ex)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }

        } ?: imageProxy.close() // close if image not found either


    }

}

