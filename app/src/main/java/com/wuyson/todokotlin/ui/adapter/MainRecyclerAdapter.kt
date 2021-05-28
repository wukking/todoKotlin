package com.wuyson.todokotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wuyson.todokotlin.databinding.ItemMainListBinding
import com.wuyson.todokotlin.entity.local.PageEntity

class MainRecyclerAdapter(private val datas: List<PageEntity>) :
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
    fun bind(data: PageEntity) {
        itemBinding.tvTitle.text = data.title
    }
}