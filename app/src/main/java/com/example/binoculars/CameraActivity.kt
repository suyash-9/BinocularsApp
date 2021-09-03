package com.example.binoculars

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_camera2.*
import java.io.File

class CameraActivity : AppCompatActivity() {

    var camera:Camera?=null
    var preview:Preview?=null
    var imagecapture:ImageCapture?=null
    //var cameraSelector:CameraSelector?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera2)

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==PERMISSION_GRANTED){
            startCamera()
        }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),0)
        }
        capturebtn.setOnClickListener {
            takePhoto()
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==PERMISSION_GRANTED){
            startCamera()
        }
        else{
            Toast.makeText(this,"Please accept the permission",Toast.LENGTH_LONG).show()
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun startCamera() {
        val cameraProviderFuture=ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
               val cameraProvider=cameraProviderFuture.get()
            preview=Preview.Builder().build()
            preview?.setSurfaceProvider(cameraView.getSurfaceProvider())
            imagecapture=ImageCapture.Builder().build()
            val cameraSelector=CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
            cameraProvider.unbindAll()
            camera=cameraProvider.bindToLifecycle(this,cameraSelector,preview,imagecapture)

        },ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val photofile= File(externalMediaDirs.firstOrNull(),"Binoculars - ${System.currentTimeMillis()}.jpg")
        val output=ImageCapture.OutputFileOptions.Builder(photofile).build()
        imagecapture?.takePicture(output,ContextCompat.getMainExecutor(this),object :ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Toast.makeText(applicationContext,"Image Saved",Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: ImageCaptureException) {
                TODO("Not yet implemented")
            }

        })

    }
}