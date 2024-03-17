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
    companion object {
        private const val TAG = "MainActivity"
        const val PREFS_NAME = "MyPrefsFile"
        const val IMAGE_KEY = "imageKey"
        const val TEXT_KEY = "textKey"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val imageIds = intArrayOf(
            R.drawable.img1,  // Replace 'image1' with your actual image file name without extension
            R.drawable.img2,
            R.drawable.img3
        )
        var pref = applicationContext.getSharedPreferences("MyPref", 0) // 0 - for private mode

  //      var editor = pref.edit()
//        val settings = getPreferences(Context.MODE_PRIVATE)
//
//        var savedImageId = settings.getInt(IMAGE_KEY, R.drawable.img3)
//        Log.d(TAG,"get from saved")
//        Log.d(TAG,savedImageId.toString())
//
//        val savedText = settings.getString(TEXT_KEY, "Default") // Default is an empty string
//        Log.d(TAG, "On Create")
//        Log.d(TAG, savedImageId.toString())
//        Log.d(TAG, savedText.toString())

        val savedText = pref.getString("text", "Default"); // getting String
        val savedImageId = pref.getInt("image_id", R.drawable.img1); // getting Integer


        binding.imageView.setImageResource(savedImageId)
        binding.editTextText.setText(savedText)

        binding.button.setOnClickListener { // Randomly select an image ID
            randomImageId = imageIds[Random.nextInt(3)]

            binding.imageView.setImageResource(randomImageId)
            Log.d(TAG, randomImageId.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "On Destroy")
        val pref = applicationContext.getSharedPreferences("MyPref", 0) // 0 - for private mode

        val editor = pref.edit()


        editor.putInt("image_id", randomImageId)
        editor.putString("text", binding.editTextText.text.toString())

//        editor.putString("key_name", "string value"); // Storing string
//        editor.putInt("key_name", "int value"); // Storing integer
//
        editor.commit(); // commit changes

//        with (settings.edit()) {
//            Log.d(TAG, randomImageId.toString())
//            Log.d(TAG, binding.editTextText.text.toString())
//            putInt(IMAGE_KEY, randomImageId)
//            putString(TEXT_KEY, binding.editTextText.text.toString())
//            apply()
//        }

//        val editor = settings.edit()

//        val currentText = binding.editTextText.text.toString()
//
//
//
//
//        editor.apply()
    }


}