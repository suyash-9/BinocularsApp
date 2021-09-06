package com.example.binoculars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_base_lens.*
import kotlinx.android.synthetic.main.activity_base_lens.*
import kotlinx.android.synthetic.main.bottomsheet_fragment.*

abstract class BaseLensActivity : AppCompatActivity() {
    companion object {
        @JvmStatic
        val CAMERA_PERM_CODE = 422
    }



    abstract val imageAnalyzer: ImageAnalysis.Analyzer
    //abstract val str:String
    protected lateinit var imageAnalysis: ImageAnalysis
    private var isScannerOn = false

    private fun askCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
            CAMERA_PERM_CODE
        )
    }
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(
            Runnable {
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(previewBarcode.getSurfaceProvider())
                    }

                imageAnalysis = ImageAnalysis.Builder()
                    .build()

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)
                } catch (ex: Exception) {
                    Log.e("CAM", "Error bindind camera", ex)
                }

            },
            ContextCompat.getMainExecutor(this)

        )

    }

    abstract fun startScanner()

    open fun stopScanner() {
        imageAnalysis.clearAnalyzer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_lens)
        askCameraPermission()

        btnStartScanner.setOnClickListener {
            // invert the scanner status
            isScannerOn=!isScannerOn
            // if the scanner is on, then turn it off and vice-versa
            if(isScannerOn) {
                stopScanner()
                btnStartScanner.text = "start scanner"
            } else {
                startScanner()
                btnStartScanner.text = "stop scanner"
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Permission Error")
                    .setMessage("Camera Permission not provided")
                    .setPositiveButton("OK") { _, _ -> finish() }
                    .setCancelable(false)
                    .show()
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}