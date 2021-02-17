package com.example.islamy.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.holyquran.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler()
            .postDelayed(
                {
                    startActivity(Intent(this, Home::class.java))
                    finish()
                },
                3000
            );
    }
}