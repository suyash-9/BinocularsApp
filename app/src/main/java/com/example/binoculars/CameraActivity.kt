package com.example.binoculars

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
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
    companion object{
        @JvmStatic val PHOTO_REQ_CODE=234
        @JvmStatic val EXTRA_DATA="data"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera2)
        supportActionBar!!.title = "Go Back"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        takepicbtn.setOnClickListener {
                val takephotointent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takephotointent, PHOTO_REQ_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == MainActivity.PHOTO_REQ_CODE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get(MainActivity.EXTRA_DATA) as Bitmap
            image.setImageBitmap(imageBitmap)
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

}