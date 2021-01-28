package com.lucasdias.catanddogsearcher.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.lucasdias.base.presentation.BaseActivity
import com.lucasdias.catanddogsearcher.R
import com.lucasdias.catanddogsearcher.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        setupLogo()
    }

    override fun onResume() {
        super.onResume()

        startNextViewWithDelay()
    }

    private fun setupToolbar() {
        supportActionBar?.hide()
    }

    private fun setupLogo() {
        binding.logoSplashActivity.titleStartLogoView.setTextColor(this.getColor(R.color.text_fourth))
        binding.logoSplashActivity.titleMiddleLogoView.setTextColor(this.getColor(R.color.text_second))
        binding.logoSplashActivity.titleEndLogoView.setTextColor(this.getColor(R.color.text_fourth))
    }

    private fun startNextViewWithDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            BaseActivity.launch(
                this,
                R.navigation.navigation_animal,
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
        }, DELAY_TIME)
    }

    companion object {
        private const val DELAY_TIME = 3000L
    }
}
