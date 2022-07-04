package com.example.formybabies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.formybabies.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regButton.setOnClickListener{
            intent.putExtra("test", "${binding.contentEt.text}")
            intent.putExtra("hi", "${binding.titleEt.text}")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}