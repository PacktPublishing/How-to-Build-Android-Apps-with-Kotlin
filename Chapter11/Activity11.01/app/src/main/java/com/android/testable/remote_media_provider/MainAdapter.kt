package com.android.testable.remote_media_provider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.testable.remote_media_provider.repository.DogUi
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_dog_item.view.*

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

    inner class DogViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                val position = adapterPosition
                if (position > RecyclerView.NO_POSITION) {
                    onRowClickListener.invoke(dogs[position])
                }
            }
        }

        fun bind(dog: DogUi) {
            containerView.view_dog_item_url_text_view.text = dog.url
        }
    }
}