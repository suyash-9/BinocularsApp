package com.example.binoculars.textrecognition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.binoculars.BaseLensActivity
import com.example.binoculars.BottomSheetFragment
import kotlinx.android.synthetic.main.activity_base_lens.*
import kotlinx.android.synthetic.main.bottomsheet_fragment.*

class TextRecognitionActivity : BaseLensActivity() {
    override val imageAnalyzer = TextAnalyzer()

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
        }
    }

}