package com.github.desfate.commonkit.views

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.github.desfate.commonkit.R
import com.github.desfate.commonlib.views.progress.WaterWaveProgress

class ProgressActivity : AppCompatActivity(), View.OnClickListener{

    lateinit var waterWaveProgress: WaterWaveProgress;
    var progress = MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)
        waterWaveProgress = findViewById(R.id.waterProgress);
        val add = findViewById<Button>(R.id.add)
        add.setOnClickListener(this)
        val reduce = findViewById<Button>(R.id.reduce)
        reduce.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.add -> progress.postValue(waterWaveProgress.progress ++)
            R.id.reduce -> progress.postValue(waterWaveProgress.progress --)
        }
    }


}