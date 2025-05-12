package com.example.testfileaccessofpc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private val baseUrl = "http://192.168.0.0:0000/image"  // Base URL for images

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Start fetching data in background
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Request image list from server
                val request = Request.Builder()
                    .url("http://192.168.0.0:0000/list/image/")
                    .build()

                val response = client.newCall(request).execute()
                val data = response.body?.string()

                if (response.isSuccessful && data != null) {
                    val files = JSONArray(data)
                    val imageList = mutableListOf<String>()

                    // Add image URLs to the list
                    for (i in 0 until files.length()) {
                        imageList.add(files.getString(i))
                    }

                    // Update UI on the main thread
                    withContext(Dispatchers.Main) {
                        // Set RecyclerView adapter with image list
                        recyclerView.adapter = ImageAdapter(imageList, baseUrl) { imageUrl ->
                            // Open ImageViewerActivity when an image is clicked
                            val intent = Intent(this@MainActivity, ImageViewerActivity::class.java)
                            intent.putExtra("image_url", imageUrl)
                            startActivity(intent)
                        }
                    }
                }
            } catch (e: Exception) {
                // Show error message if network request fails
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}




























//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import org.json.JSONArray
//import kotlin.jvm.java
//
//class MainActivity : AppCompatActivity() {
//
//    private val client = OkHttpClient()
//    private val baseUrl = "http://192.168.29.188:5000/image/image"  // Double "image" as per your API path
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val request = Request.Builder()
//                    .url("http://192.168.29.188:5000/list/image/")
//                    .build()
//
//                val response = client.newCall(request).execute()
//                val data = response.body?.string()
//
//                if (response.isSuccessful && data != null) {
//                    val files = JSONArray(data)
//                    val imageList = mutableListOf<String>()
//
//                    for (i in 0 until files.length()) {
//                        imageList.add(files.getString(i))
//                    }
//
//                    withContext(Dispatchers.Main) {
//                        recyclerView.adapter = ImageAdapter(imageList, baseUrl) { imageUrl ->
//                            val intent = Intent(this@MainActivity, ImageViewerActivity::class.java)
//                            intent.putExtra("image_url", imageUrl)
//                            startActivity(intent)
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
//                }
//            }
//        }
//    }
//}



























//
//import android.os.Bundle
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import kotlinx.coroutines.*
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import org.json.JSONArray
//
//class MainActivity : AppCompatActivity() {
//
//    private val client = OkHttpClient()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val textView = findViewById<TextView>(R.id.textView)
//
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val request = Request.Builder()
//                    .url("http://192.168.29.188:5000/list/image/")  // Replace with your real IP
//                    .build()
//
//                val response = client.newCall(request).execute()
//                val data = response.body?.string()
//
//                if (response.isSuccessful && data != null) {
//                    val files = JSONArray(data)
//                    val fileList = StringBuilder()
//
//                    for (i in 0 until files.length()) {
//                        fileList.append(files.getString(i)).append("\n")
//                    }
//
//                    withContext(Dispatchers.Main) {
//                        textView.text = fileList.toString()
//                    }
//                } else {
//                    withContext(Dispatchers.Main) {
//                        textView.text = "Error: Unable to load data"
//                    }
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    textView.text = "Exception: ${e.message}"
//                }
//            }
//        }
//    }
//}
