package com.lucasdias.feature_animal.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.lucasdias.extensions.gone
import com.lucasdias.extensions.loadImage
import com.lucasdias.feature_animal.R
import com.lucasdias.feature_animal.databinding.AnimalDetailSectionBinding
import com.lucasdias.feature_animal.databinding.FragmentAnimalDetailBinding

class AnimalDetailFragment : Fragment(R.layout.fragment_animal_detail) {

    private val args: AnimalDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentAnimalDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBinding(view)
        setupImage()
        setupSections()
        setupButton()
    }

    private fun setupBinding(view: View) {
        binding = FragmentAnimalDetailBinding.bind(view)
    }

    private fun setupImage() {
        binding.imageFragmentAnimalDetail.loadImage(args.animal.imageUrl, R.drawable.picture_place_holder)
    }

    private fun setupButton() = with(binding.wikipediaButtonFragmentAnimalDetail) {
        if (args.animal.wikipediaUrl.isNullOrEmpty()) this.gone()

        this.onComponentClickListener {
            val intent = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            intent.data = Uri.parse(args.animal.wikipediaUrl)
            startActivity(intent)
        }
    }

    private fun setupSections() = with(args.animal) {
        setupSection(binding.nameFragmentAnimalDetail, R.string.name_title_fragment_animal_detail, name)
        setupSection(binding.temperamentFragmentAnimalDetail, R.string.temperament_title_fragment_animal_detail, temperament)
        setupSection(binding.lifetimeFragmentAnimalDetail, R.string.lifetime_title_fragment_animal_detail, lifetime)
    }

    private fun setupSection(
        section: AnimalDetailSectionBinding,
        @StringRes titleRes: Int,
        text: String?
    ) {
        if (text.isNullOrEmpty()) section.layoutAnimalDetailSection.gone()

        section.titleAnimalDetailSection.text = getText(titleRes)
        section.textAnimalDetailSection.text = text
    }
}
