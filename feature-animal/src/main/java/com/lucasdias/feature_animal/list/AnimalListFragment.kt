package com.lucasdias.feature_animal.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.lucasdias.feature_animal.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnimalListFragment : Fragment(R.layout.fragment_animal_list) {

    private val args: AnimalListFragmentArgs by navArgs()
    private val viewModel by viewModel<AnimalListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val response = viewModel.executeRequest()
        Log.i("Search cat", "RESPONSE: $response")
    }
}
