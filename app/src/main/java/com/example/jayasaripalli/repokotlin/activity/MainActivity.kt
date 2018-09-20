package com.example.jayasaripalli.repokotlin.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import com.example.jayasaripalli.repokotlin.R

class MainActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var uName: String
    private lateinit var submitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.ed_username)
        submitBtn = findViewById(R.id.btn_submit)

        submitBtn.setOnClickListener {
            hideSoftKeyboard()
            uName = username.text.toString()
            val intent = Intent(this, RepoDetailActivity::class.java)
            intent.putExtra("username", uName)
            startActivity(intent)
        }
    }

    private fun hideSoftKeyboard() {
        val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager.isActive && this.currentFocus != null) {
            inputMethodManager
                    .hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        }
    }
}
