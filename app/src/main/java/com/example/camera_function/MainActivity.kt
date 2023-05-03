package com.example.camera_function

import android.app.Activity
import android.app.Instrumentation
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCameraLeft = findViewById<Button>(R.id.btn_left)
        val btnCameraRight = findViewById<Button>(R.id.btn_right)

        btnCameraLeft.setOnClickListener{
            Toast.makeText(this, "left button pressed", Toast.LENGTH_LONG).show()
            startForResultLeft.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
        btnCameraRight.setOnClickListener{
            Toast.makeText(this, "right button pressed", Toast.LENGTH_LONG).show()
            startForResultRight.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }

    private val startForResultLeft = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: androidx.activity.result.ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            val imageBitmap = intent?.extras?.get("data") as Bitmap
            val image_left = findViewById<ImageView>(R.id.iv_image_left)
            image_left.setImageBitmap(imageBitmap)
        }
    }

    private val startForResultRight = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: androidx.activity.result.ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            val imageBitmap = intent?.extras?.get("data") as Bitmap
            val image_right = findViewById<ImageView>(R.id.iv_image_right)
            image_right.setImageBitmap(imageBitmap)
        }
    }
}