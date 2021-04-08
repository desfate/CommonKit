package com.github.desfate.commonkit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.desfate.commonkit.utils.FastRecyclerAdapter
import com.github.desfate.commonkit.utils.FastViewHolder
import com.github.desfate.commonkit.views.ProgressActivity

class ViewsActivity : AppCompatActivity() {

    lateinit var recyclerView :RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_views)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = FastRecyclerAdapter(items = listOf(
            "progress","others"
        ), layoutCallBack = {
            R.layout.views_item
        }, convertCallBack = { mViewHolder: FastViewHolder, s: String, i: Int ->
            mViewHolder.getView<Button>(R.id.item_text)?.text = s
            mViewHolder.getView<Button>(R.id.item_text)?.setOnClickListener {
                if (s == "progress") {
                    startActivity(Intent(this, ProgressActivity::class.java))
                } else {

                }
            }
        })
    }



}