package com.example.maintain

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.maintain.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var randomImageId: Int = 0 // Assuming a default value. Adjust as necessary.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val imageIds = intArrayOf(
            R.drawable.img1,  // Replace 'image1' with your actual image file name without extension
            R.drawable.img2,
            R.drawable.img3
        )

        val settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Retrieve saved state
        val savedImageId = settings.getInt(IMAGE_KEY, R.drawable.img1) // Provide a default value if it doesn't exist
        val savedText = settings.getString(TEXT_KEY, "Default") // Default is an empty string

        binding.button.setOnClickListener { // Randomly select an image ID
            randomImageId = imageIds[Random.nextInt(imageIds.size)]

            binding.imageView.setImageResource(randomImageId)
        }

        binding.imageView.setImageResource(savedImageId)
        binding.editTextText.setText(savedText)



    }

    override fun onDestroy() {
        super.onDestroy()

        val settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = settings.edit()

        val currentText = binding.editTextText.text.toString()

        editor.putInt(IMAGE_KEY, randomImageId)
        editor.putString(TEXT_KEY, currentText)

        editor.apply()
    }


    companion object {
        const val PREFS_NAME = "MyPrefsFile"
        const val IMAGE_KEY = "imageKey"
        const val TEXT_KEY = "textKey"
    }

}