package com.lucasdias.feature_animal.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasdias.domain.model.Animal
import com.lucasdias.feature_animal.R
import com.lucasdias.feature_animal.databinding.AnimalListItemBinding
import com.lucasdias.ui_components.card.model.CardComponentProperties
import com.lucasdias.ui_components.card.model.CardImageProperties

class AnimalListAdapter(private val navigateToAnimalDetail: ((Animal) -> Unit)) : RecyclerView.Adapter<AnimalListAdapter.ViewHolder>() {

    private val animals = mutableListOf<Animal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AnimalListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(navigateToAnimalDetail, binding)
    }

    override fun getItemCount(): Int = animals.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(animal = animals[position])
    }

    fun updateAnimalList(animals: List<Animal>) {
        this.animals.clear()
        this.animals.addAll(animals)
        this.animals.sortBy { it.name }

        notifyDataSetChanged()
    }

    class ViewHolder(
        private val navigateToAnimalDetail: (Animal) -> Unit,
        private val binding: AnimalListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(animal: Animal) {
            with(animal) {
                val imageProperties = CardImageProperties(animal.imageUrl, R.drawable.picture_place_holder)
                val cardProperties = CardComponentProperties(id, name, imageProperties, null)

                binding.layoutCardComponent.applyProperties(cardProperties)
                binding.layoutCardComponent.onComponentClickListener {
                    navigateToAnimalDetail(animal)
                }
            }
        }
    }
}
