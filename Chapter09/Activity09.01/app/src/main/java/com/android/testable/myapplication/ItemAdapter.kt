package com.android.testable.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val layoutInflater: LayoutInflater,
    private val onRowClickListener: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val items = mutableListOf<Item>()

    fun addItems(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(layoutInflater.inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

        private val itemTextView: TextView = containerView.findViewById(R.id.item_text_view)

        init {
            containerView.setOnClickListener {
                val position = adapterPosition
                if (position > RecyclerView.NO_POSITION) {
                    onRowClickListener.invoke(items[position])
                }
            }
        }

        fun bind(item: Item) {
            itemTextView.text = item.text
        }
    }
}