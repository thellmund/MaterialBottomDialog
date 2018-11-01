package com.hellmund.materialbottomdialog

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openLightMode.setOnClickListener {
            startActivity(Intent(this, KotlinActivity::class.java))
        }

        openDarkMode.setOnClickListener {
            startActivity(Intent(this, JavaActivity::class.java))
        }
    }

}
