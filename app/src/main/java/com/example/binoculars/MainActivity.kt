package com.example.binoculars

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.binoculars.barcode.BarcodeActivity
import com.example.binoculars.facedetect.FaceDetectActivity
import com.example.binoculars.imagelabeler.ImageLabelingActivity
import com.example.binoculars.textrecognition.TextRecognitionActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),RecyclerAdapter.OnItemClickListener {
    //lateinit var str:String
    companion object{
        @JvmStatic val PHOTO_REQ_CODE=234
        @JvmStatic val EXTRA_DATA="data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binocularRV.layoutManager=LinearLayoutManager(this)
        val adapter=RecyclerAdapter(this)
        binocularRV.adapter=adapter





//        takepicbtn.setOnClickListener {
//                val takephotointent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            startActivityForResult(takephotointent, PHOTO_REQ_CODE)
//        }
//
//        buttonActivitybtn.setOnClickListener {
//            val intent=Intent(this,CameraActivity2::class.java)
//            //intent.putExtra("Ans",str)
//            startActivity(Intent(this,CameraActivity2::class.java))
//        }
//        btnBarcodeActivity.setOnClickListener {
//            startActivity(Intent(this, BarcodeActivity::class.java))
//        }
//        btnFaceDetectActivity.setOnClickListener {
//            startActivity(Intent(this, FaceDetectActivity::class.java))
//        }
//        btnLabelerActivity.setOnClickListener {
//            startActivity(Intent(this, ImageLabelingActivity::class.java))
//        }
//        btnTextRecogActivity.setOnClickListener {
//            startActivity(Intent(this, TextRecognitionActivity::class.java))
//        }
    }

    override fun onItemClick(position: Int) {
        if(position==0){
            startActivity(Intent(this,CameraActivity::class.java))
        }
        if(position==1){
            startActivity(Intent(this,CameraActivity2::class.java))
        }
        if(position==2){
            startActivity(Intent(this, FaceDetectActivity::class.java))
        }
        if(position==3){
            startActivity(Intent(this, TextRecognitionActivity::class.java))
        }
        if(position==4){
            startActivity(Intent(this, ImageLabelingActivity::class.java))
        }
        if(position==5){
            startActivity(Intent(this, BarcodeActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PHOTO_REQ_CODE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get(EXTRA_DATA) as Bitmap
            //image.setImageBitmap(imageBitmap)
            return
        }

        super.onActivityResult(requestCode, resultCode, data)
    }


}