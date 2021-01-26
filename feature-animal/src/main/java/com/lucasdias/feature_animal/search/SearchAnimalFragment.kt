package com.lucasdias.feature_animal.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lucasdias.extensions.findNavController
import com.lucasdias.feature_animal.R
import com.lucasdias.feature_animal.databinding.FragmentSearchAnimalBinding

class SearchAnimalFragment : Fragment(R.layout.fragment_search_animal) {

    private lateinit var binding: FragmentSearchAnimalBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBinding(view)
        setupSearchButton()
    }

    private fun setupBinding(view: View) {
        binding = FragmentSearchAnimalBinding.bind(view)
    }

    private fun setupSearchButton() {
        with(binding) {
            buttonSearchAnimalFragment.onComponentClickListener {
                val searchText = searchTextInputSearchAnimalFragment.getTypedText()
                navigateToAnimalListFragment(searchText)
            }
        }
    }

    private fun navigateToAnimalListFragment(searchText: String) {
        val directions = SearchAnimalFragmentDirections.navigateToAnimalList(searchText)
        findNavController().navigate(directions)
    }
}
