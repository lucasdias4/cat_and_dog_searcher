package com.lucasdias.feature_animal.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasdias.domain.model.Animal
import com.lucasdias.feature_animal.databinding.AnimalListItemBinding
import com.lucasdias.ui_components.card.model.CardComponentProperties

class AnimalListAdapter : RecyclerView.Adapter<AnimalListAdapter.ViewHolder>() {

    private val animals = mutableListOf<Animal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AnimalListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = animals.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(animal = animals[position])
    }

    fun updateAnimalList(comicSummaries: List<Animal>) {
        this.animals.addAll(comicSummaries)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: AnimalListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        // TODO: Implement image
        fun bind(animal: Animal) {
            with(animal) {
                val properties = CardComponentProperties(id, name, null, null)
                binding.layoutCardComponent.applyProperties(properties)
            }
        }
    }
}
