package com.example.binoculars.textrecognition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.binoculars.BaseLensActivity
import com.example.binoculars.BottomSheetFragment
import com.example.binoculars.R
import kotlinx.android.synthetic.main.activity_base_lens.*
import kotlinx.android.synthetic.main.bottomsheet_fragment.*

class TextRecognitionActivity : BaseLensActivity() {
    var str=""
   // override val imageAnalyzer = TextAnalyzer()
   override val imageAnalyzer = TextAnalyzer(
       onSuccessListener =  { result ->
           //ansView.text = result
           str=result
       },
       onErrorListener = {

       }
   )

    override fun startScanner() {
        startTextRecognition()
    }

    private fun startTextRecognition() {
        imageAnalysis.setAnalyzer(
            ContextCompat.getMainExecutor(this),
            imageAnalyzer
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bottomSheetFragment = BottomSheetFragment()
        bottombtn.setOnClickListener {
            bottomSheetFragment.show(supportFragmentManager, "BottomSheetDialog")
            val b = Bundle()
            b.putString("Ans_key",str)
            bottomSheetFragment.setArguments(b)
//            val textView = findViewById<TextView>(R.id.ansView)
//            textView.text = str
        }
    }

}