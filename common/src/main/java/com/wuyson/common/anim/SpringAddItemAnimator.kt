package com.wuyson.common.anim

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class SpringAddItemAnimator :DefaultItemAnimator(){
    private val pendingAdds = mutableListOf<RecyclerView.ViewHolder>()

    //addItem时的动画
    override fun animateAdd(holder: RecyclerView.ViewHolder): Boolean {
        holder.itemView.alpha = 0f
        holder.itemView.translationY = holder.itemView.bottom /3f
        pendingAdds.add(holder)
        return true
    }

    override fun runPendingAnimations() {
        super.runPendingAnimations()
        if (pendingAdds.isNotEmpty()){
            for (i in pendingAdds.indices.reversed()){

            }
        }
    }


    override fun endAnimation(item: RecyclerView.ViewHolder) {
        super.endAnimation(item)
    }

    override fun endAnimations() {
        super.endAnimations()
    }

    override fun isRunning(): Boolean {
        return super.isRunning()
    }


}
