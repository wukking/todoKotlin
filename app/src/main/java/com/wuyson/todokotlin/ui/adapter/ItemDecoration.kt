package com.wuyson.todokotlin.ui.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wuyson.todokotlin.R

class ItemDecoration(private val context:Context) :RecyclerView.ItemDecoration() {
    private var mDividerHeight = context.resources.getDimensionPixelSize(R.dimen.app_dp_2);

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = mDividerHeight
        outRect.left = mDividerHeight
        outRect.right = mDividerHeight
        outRect.top = mDividerHeight
    }
}