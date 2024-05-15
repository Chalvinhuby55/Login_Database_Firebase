package com.example.loginapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginapplication.databinding.ActivityDetailBinding

class Detail : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        mIntent.putExtra("DESKT", desk)
//        mIntent.putExtra("JUDULT", judul)
//        mIntent.putExtra("IMGT", img)


        val intess = intent
        var desk = intess.getStringExtra("DESKT")
        var judul = intess.getStringExtra("JUDULT")
        var img = intess.getStringExtra("IMGT")

        binding.detail.text = desk
        binding.judul.text = judul
        binding.dimg.loadImage(img)


          }
}