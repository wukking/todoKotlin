package com.wuyson.todokotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wuyson.todokotlin.databinding.ItemMainListBinding
import com.wuyson.todokotlin.entity.local.PageEntity

class MainRecyclerAdapter(private val datas: List<PageEntity>) :
    RecyclerView.Adapter<MainHolder>() {

    private var mListener:OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding =
            ItemMainListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val entity = datas[position]
        holder.bind(entity)
        holder.itemView.setOnClickListener{
            mListener?.onItemClick(entity)
        }
    }

    override fun getItemCount(): Int = if (datas.isNotEmpty()) {
        datas.size
    } else {
        0
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.mListener = listener
    }
}

class MainHolder(private val itemBinding: ItemMainListBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(data: PageEntity) {
        itemBinding.tvTitle.text = data.title
    }
}

interface OnItemClickListener{
    fun onItemClick(entity:PageEntity)
}