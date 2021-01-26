package com.lucasdias.catanddogsearcher.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.lucasdias.catanddogsearcher.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()

        setupToolbar()
        startNextViewWithDelay()
    }

    private fun setupToolbar() {
        supportActionBar?.hide()
    }

    private fun startNextViewWithDelay() {
        Handler(Looper.getMainLooper()).postDelayed({

        }, DELAY_TIME)
    }

    companion object {
        private const val DELAY_TIME = 3000L
    }
}
