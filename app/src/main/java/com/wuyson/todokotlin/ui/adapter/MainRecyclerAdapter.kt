package com.wuyson.todokotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wuyson.todokotlin.databinding.ItemMainListBinding

class MainRecyclerAdapter(private val datas: List<String>) :
    RecyclerView.Adapter<MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding =
            ItemMainListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = if (datas.isNotEmpty()) {
        datas.size
    } else {
        0
    }
}

class MainHolder(private val itemBinding: ItemMainListBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(data: String) {
        itemBinding.tvTitle.text = data
    }
}