package com.github.desfate.commonlib.kotlin

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


typealias ConvertCallBack<T> = (holder: FastViewHolder, data: T, position: Int) -> Unit
typealias LayoutCallBack = (layoutId: Int) -> Int

class FastRecyclerAdapter <T>(var items: List<T>, layoutCallBack: LayoutCallBack ?= null, convertCallBack: ConvertCallBack<T> ?= null) : RecyclerView.Adapter<FastViewHolder> (){

    var layoutCallBacks: LayoutCallBack = layoutCallBack!!
    var convertCallBacks: ConvertCallBack<T> = convertCallBack!!

    override fun onBindViewHolder(p0: FastViewHolder, p1: Int) {
        convertCallBacks.invoke(p0, items.get(p1), p1)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FastViewHolder {
        return FastViewHolder.getViewHolde(p0, layoutCallBacks.invoke(p1))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(list: List<T>){
        items = list
    }
}