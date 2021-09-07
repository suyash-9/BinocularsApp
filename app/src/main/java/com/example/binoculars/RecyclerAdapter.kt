package com.example.binoculars

import android.content.Context
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    val pics: IntArray=intArrayOf(R.drawable.sytemcamera,R.drawable.camerax,R.drawable.facedetection,R.drawable.textrecognition,R.drawable.imagelabeling,R.drawable.barcode)
    val name:Array<String> = arrayOf("System Camera","Camera X","Face Detection","Text Recognition","Image Labelling","Barcode Scanner")
    val descs:Array<String> = arrayOf("Opens up your System Camera","Open the Camera X","Detect faces and facial landmarks.","Recognize and extract text from images","Identify objects, locations, activities, animal species, products, and more. ","Scan and process barcodes. Supports most standard 1D and 2D formats.")
     inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val image:ImageView
        val name: TextView
        val desc:TextView
        init {
            image=itemView.findViewById(R.id.imageView2)
            name=itemView.findViewById(R.id.textView3)
            desc=itemView.findViewById(R.id.textView4)
            itemView.setOnClickListener(this)
        }

         override fun onClick(p0: View?) {
             val position=adapterPosition
             if (position!=RecyclerView.NO_POSITION) {
                 listener.onItemClick(position)
             }
         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {


        //var currentposition=holder.adapterPosition

        holder.image.setImageResource(pics[position])
        holder.name.text=name[position]
        holder.desc.text=descs[position]

        //val animation:Animation =AnimationUtils.loadAnimation(this@RecyclerAdapter,android.R.anim.slide_in_left)
        //holder.itemView.startAnimation()



    }

    override fun getItemCount(): Int {
        return pics.size
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }




}


