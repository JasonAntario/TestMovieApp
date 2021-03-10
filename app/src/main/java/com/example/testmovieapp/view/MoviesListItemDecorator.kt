package com.example.testmovieapp.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MoviesListItemDecorator(private val spacing: Int) : RecyclerView.ItemDecoration() {


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        val position = parent.getChildAdapterPosition(view)
//        val column = position % spanCount
//        if (includeEdge) {
//            outRect.left = spacing - column * spanCount
//            outRect.right = (column + 1) * spacing / spanCount
//            if (position < spanCount) {
//                outRect.top = spacing
//            }
//            outRect.bottom = spacing
//        } else {
//            outRect.left = column * spacing / spanCount
//            outRect.right = spacing - (column + 1) * spacing / spanCount
//            if (position >= spanCount) {
//                outRect.top = spacing
//            }
//        }
        outRect.set(spacing, 0, spacing, spacing)
    }
}