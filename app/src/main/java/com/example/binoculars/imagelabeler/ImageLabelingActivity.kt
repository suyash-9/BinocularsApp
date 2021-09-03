package com.example.binoculars.imagelabeler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.binoculars.BaseLensActivity
import com.example.binoculars.BottomSheetFragment
import kotlinx.android.synthetic.main.activity_base_lens.*
import kotlinx.android.synthetic.main.bottomsheet_fragment.*

class ImageLabelingActivity : BaseLensActivity() {
    override val imageAnalyzer = ImageLabelAnalyzer()

    override fun startScanner() {
        startImageLabeling()
    }

    private fun startImageLabeling() {
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