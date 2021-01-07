package com.android.testable.remote_media_provider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.testable.remote_media_provider.repository.DogUi

class MainAdapter(
    private val layoutInflater: LayoutInflater,
    private val onRowClickListener: (DogUi) -> Unit
) : RecyclerView.Adapter<MainAdapter.DogViewHolder>() {

    private val dogs = mutableListOf<DogUi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder =
        DogViewHolder(layoutInflater.inflate(R.layout.view_dog_item, parent, false))

    override fun getItemCount(): Int = dogs.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(dogs[position])
    }

    fun updateDogs(dogs: List<DogUi>) {
        this.dogs.clear()
        this.dogs.addAll(dogs)
        this.notifyDataSetChanged()
    }

    inner class DogViewHolder(containerView: View) :
        RecyclerView.ViewHolder(containerView) {

        private val urlTextView: TextView =
            containerView.findViewById<TextView>(R.id.view_dog_item_url_text_view)

        init {
            containerView.setOnClickListener {
                val position = adapterPosition
                if (position > RecyclerView.NO_POSITION) {
                    onRowClickListener.invoke(dogs[position])
                }
            }
        }

        fun bind(dog: DogUi) {
            urlTextView.text = dog.url
        }
    }
}