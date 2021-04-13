package com.github.desfate.commonkit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutinesActivity : AppCompatActivity() {

//    val scope: GlobalScope = GlobalScope
//    val scope: Lifecycle

    companion object {
        const val TAG = "CoroutinesActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        startTest();
    }

    private fun startTest(){
        GlobalScope.launch {
            delay(10000L)
            println(TAG + "GlobalScope thread")
        }
        println(TAG + "main thread")
    }

}