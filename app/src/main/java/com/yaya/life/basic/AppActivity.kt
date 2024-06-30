package com.yaya.life.basic

import androidx.appcompat.app.AppCompatActivity

abstract class AppActivity : AppCompatActivity() {

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
