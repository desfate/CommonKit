package com.github.desfate.commonkit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() ,
    View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.views_btn).setOnClickListener(this)
        findViewById<Button>(R.id.coroutines_btn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.views_btn -> startActivity(Intent(this, ViewsActivity::class.java))
            R.id.coroutines_btn -> startActivity(Intent(this, CoroutinesActivity::class.java))
        }
    }
}