package com.lucasdias.feature_animal.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.lucasdias.feature_animal.R

class AnimalListFragment : Fragment(R.layout.fragment_animal_list) {

    private val args: AnimalListFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), args.searchText, LENGTH_SHORT).show()
    }
}
