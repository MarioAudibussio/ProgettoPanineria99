package com.example.schedacibo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.schedacibo.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("NAVIGATE_TO_FRAGMENT", "account")
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(intent)
            finish()
        }
        setContentView(binding.root)
    }
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        // Nascondi la tastiera se si tocca lo schermo
        currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        return super.dispatchTouchEvent(event)
    }

}