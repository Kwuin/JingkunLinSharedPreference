package com.example.maintain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.maintain.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val imageIds = intArrayOf(
            R.drawable.img1,  // Replace 'image1' with your actual image file name without extension
            R.drawable.img2,
            R.drawable.img3
        )
        binding.button.setOnClickListener { // Randomly select an image ID
            val randomImageId: Int = imageIds[Random.nextInt(imageIds.size)]
            // Set the selected image as the source for the ImageView
            binding.imageView.setImageResource(randomImageId)
        }

    }
}