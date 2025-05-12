package com.example.testfileaccessofpc

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ImageViewerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)

        val imageView = findViewById<ImageView>(R.id.fullImageView)

        // Get image URL from intent
        val imageUrl = intent.getStringExtra("image_url")

        // Load the image into the ImageView using Glide
        if (imageUrl != null) {
            Glide.with(this)
                .load("http://192.168.0.0:0000/image/$imageUrl")
                .into(imageView)
        }
    }
}



























//import android.os.Bundle
//import android.view.ViewGroup
//import android.widget.ImageView
//import androidx.appcompat.app.AppCompatActivity
//import com.bumptech.glide.Glide
//
//
//class ImageViewerActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val imageView = ImageView(this)
//        imageView.layoutParams = ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT
//        )
//        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
//        setContentView(imageView)
//
//        val imageUrl = intent.getStringExtra("image_url")
//        Glide.with(this).load(imageUrl).into(imageView)
//    }
//}