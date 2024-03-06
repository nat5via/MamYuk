package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    companion object {
        const val foodName = "foodname"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvName: TextView = findViewById(R.id.TitleFood)
        val tvDesc: TextView = findViewById(R.id.DescriptionDetail)
        val tvFood: ImageView = findViewById(R.id.DetailedImage)

        val food = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Food>(foodName, Food::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Food>(foodName)
        }

        if (food != null) {
            val text = "${food.name} "
            tvName.text = text
            tvDesc.text = food.description

            // Load image using Glide
            food.photo?.let { photoUrl ->
                Glide.with(this)
                    .load(photoUrl) // Load image from URL or drawable file name stored in photo property
                    .into(tvFood)
            }
        }
    }
}

