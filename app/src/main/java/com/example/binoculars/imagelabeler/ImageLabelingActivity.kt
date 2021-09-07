package com.example.binoculars.imagelabeler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.binoculars.BaseLensActivity
import com.example.binoculars.BottomSheetFragment
import kotlinx.android.synthetic.main.activity_base_lens.*
import kotlinx.android.synthetic.main.bottomsheet_fragment.*

class ImageLabelingActivity : BaseLensActivity() {
    var str=""
    override val imageAnalyzer = ImageLabelAnalyzer(
        onSuccessListener =  { result ->
            //ansView.text = result
            str=result
        },
        onErrorListener = {

        }
    )

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
        supportActionBar!!.title = "Image Labeler"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val bottomSheetFragment = BottomSheetFragment()
        bottombtn.setOnClickListener {
            bottomSheetFragment.show(supportFragmentManager, "BottomSheetDialog")
            val b = Bundle()
            b.putString("Ans_key",str)
            bottomSheetFragment.setArguments(b)
        }
    }
}