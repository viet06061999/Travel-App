package com.sunasterisk.travelapp.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T, V : BaseViewHolder<T>> : RecyclerView.Adapter<V>() {

    protected val items = mutableListOf<T>()

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: V, position: Int) {
        getItem(position)?.let {
            holder.onBindData(it)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (!items.isNullOrEmpty()) {
            VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_EMPTY
        }
    }

    private fun getItem(position: Int): T? =
        if (position in 0 until itemCount) items[position] else null

    open fun updateData(newItems: List<T>) {
        println(newItems)
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    companion object {
        const val VIEW_TYPE_EMPTY = 0
        const val VIEW_TYPE_NORMAL = 1
    }
}
