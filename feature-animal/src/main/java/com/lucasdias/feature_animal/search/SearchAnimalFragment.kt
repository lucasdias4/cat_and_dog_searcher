package com.lucasdias.feature_animal.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lucasdias.core.connectivity.Connectivity
import com.lucasdias.core.di.CONNECTIVITY
import com.lucasdias.domain.enum.RequestType
import com.lucasdias.extensions.animateGoneToVisible
import com.lucasdias.extensions.animateVisibleToGone
import com.lucasdias.extensions.findNavController
import com.lucasdias.extensions.setup
import com.lucasdias.extensions.showConnectivitySnackbar
import com.lucasdias.feature_animal.R
import com.lucasdias.feature_animal.databinding.FragmentSearchAnimalBinding
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class SearchAnimalFragment : Fragment(R.layout.fragment_search_animal) {

    private val connectivity by inject<Connectivity>(named(CONNECTIVITY))
    private lateinit var binding: FragmentSearchAnimalBinding
    private var requestType = RequestType.CAT

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBinding(view)
        setupSearchButton()
        setupSpinner()
        setupConnectivity()
    }

    private fun setupBinding(view: View) {
        binding = FragmentSearchAnimalBinding.bind(view)
    }

    private fun setupSearchButton() {
        with(binding) {
            buttonSearchAnimalFragment.onComponentClickListener {
                val searchText = searchTextInputSearchAnimalFragment.getTypedText()

                if (searchText.isNotEmpty()) {
                    errorMessageSearchAnimalFragment.animateVisibleToGone()
                    searchTextInputSearchAnimalFragment.notifySuccess()
                    navigateToAnimalListFragment(searchText)
                } else {
                    searchTextInputSearchAnimalFragment.notifyError()
                    errorMessageSearchAnimalFragment.animateGoneToVisible()
                }
            }
        }
    }

    private fun setupSpinner() {
        val options = listOf(RequestType.CAT.text, RequestType.DOG.text)

        binding.requestTypeSpinnerSearchAnimalFragment.setup(
            requireContext(),
            options
        ) { option -> onSpinnerOptionSelected(option) }
    }

    private fun setupConnectivity() {
        connectivity.getLiveData().observe(viewLifecycleOwner, Observer { hasNetworkConnectivity ->
            view?.showConnectivitySnackbar(hasNetworkConnectivity)
        })
    }

    private fun onSpinnerOptionSelected(option: String?) {
        requestType = RequestType.getRequestTypeByText(option)
    }

    private fun navigateToAnimalListFragment(searchText: String) {
        val directions =
            SearchAnimalFragmentDirections.navigateToAnimalList(searchText, requestType)
        findNavController().navigate(directions)
    }
}
