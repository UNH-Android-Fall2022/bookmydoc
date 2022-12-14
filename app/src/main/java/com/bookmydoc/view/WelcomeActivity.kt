package com.bookmydoc.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bookmydoc.R
import com.bookmydoc.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        binding.btnNext.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnNext -> {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
                finishAffinity()
            }
        }
    }
}