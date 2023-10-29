package com.example.student

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

       Handler(Looper.getMainLooper()).postDelayed(3000){
           startActivity(Intent(this,MainActivity::class.java))
           finish()
       }

    }


}