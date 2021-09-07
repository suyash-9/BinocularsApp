package com.example.binoculars.barcode

import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.binoculars.BaseLensActivity
import com.example.binoculars.BottomSheetFragment

import com.example.binoculars.R
import kotlinx.android.synthetic.main.activity_base_lens.*


class BarcodeActivity : BaseLensActivity() {
    //val str= intent.getStringExtra("Ans").toString()
   var str=""

//    private var bottomSheetBehaviour: BottomSheetBehavior<View?>? = null
//    bottomSheetBehaviour.setState(BottomSheetBehaviour.STATE_HIDDEN)

    //override val str=""
    override val imageAnalyzer = BarcodeAnalyzer(
        onSuccessListener =  { result ->
            //ansView.text = result
            str=result
        },
        onErrorListener = {

        }
    )
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
        supportActionBar!!.title = "Barcode/QR Code Scanner"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        var bottomSheetFragment = BottomSheetFragment()
        bottombtn.setOnClickListener {
            bottomSheetFragment.show(supportFragmentManager,"BottomSheetDialog")
            val b = Bundle()
            b.putString("Ans_key",str)
            bottomSheetFragment.setArguments(b)
//            val textView = findViewById<TextView>(R.id.ansView)
//            textView.text = str
            //startActivity(Intent(this, BarcodeAnalyzerActivity::class.java))
        }




  }





}

