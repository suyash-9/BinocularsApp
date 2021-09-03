package com.example.binoculars.barcode

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.core.content.ContextCompat
import com.example.binoculars.BaseLensActivity
import com.example.binoculars.BottomSheetFragment
import com.example.binoculars.R
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import kotlinx.android.synthetic.main.activity_base_lens.*

//var str: String? =null

class BarcodeAnalyzerActivity : BaseLensActivity() {
    //val str= intent.getStringExtra("Ans").toString()
    var str=""

//    private var bottomSheetBehaviour: BottomSheetBehavior<View?>? = null
//    bottomSheetBehaviour.setState(BottomSheetBehaviour.STATE_HIDDEN)

    //override val str=""
    override val imageAnalyzer = BarcodeAnalyzer()
    override fun startScanner() {
        scanBarcode()
    }

    private fun scanBarcode() {

        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
//

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bottomSheetFragment = BottomSheetFragment()
        bottombtn.setOnClickListener {
            bottomSheetFragment.show(supportFragmentManager,"BottomSheetDialog")
            val b = Bundle()
            b.putString("Ans",str)
            bottomSheetFragment.setArguments(b)
//            val textView = findViewById<TextView>(R.id.ansView)
//            textView.text = str
            //startActivity(Intent(this, BarcodeAnalyzerActivity::class.java))
        }




    }





}








