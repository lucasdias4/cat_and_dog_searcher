package com.lucasdias.feature_animal.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasdias.base.presentation.BaseFragment
import com.lucasdias.core.resource.observe
import com.lucasdias.domain.model.Animal
import com.lucasdias.feature_animal.R
import com.lucasdias.feature_animal.databinding.FragmentAnimalListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

@Suppress("UNCHECKED_CAST")
class AnimalListFragment : BaseFragment<List<Animal>>(
    successViewId = R.id.recycler_view_fragment_animal_list,
    loadingViewId = R.id.loading_layout_fragment_animal_list,
    errorViewId = R.id.error_view_fragment_animal_list,
    fragmentLayoutId = R.layout.fragment_animal_list
) {

    private val args: AnimalListFragmentArgs by navArgs()
    override val viewModel by viewModel<AnimalListViewModel>()
    private val adapter by inject<AnimalListAdapter> {
        parametersOf({ animal: Animal -> navigateToAnimalDetail(animal) })
    }
    private lateinit var binding: FragmentAnimalListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.setupRequest(args.searchText, args.requestType)
        super.onViewCreated(view, savedInstanceState)
        setupBinding(view)
        setupResponseObserver()
        setupRecyclerView()
    }

    private fun setupBinding(view: View) {
        binding = FragmentAnimalListBinding.bind(view)
    }

    private fun setupResponseObserver() {
        viewModel.responseLiveData.observe(
            viewLifecycleOwner,
            ::onLoading,
            ::onSuccess,
            ::onSuccessWithoutContent,
            ::onError
        )
    }

    override fun onSuccess(model: Any?) {
        super.onSuccess(model)
        model as List<Animal>

        adapter.updateAnimalList(model)
    }

    private fun setupRecyclerView() = with(binding.recyclerViewFragmentAnimalList) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setHasFixedSize(true)
        this.layoutManager = layoutManager
        this.adapter = this@AnimalListFragment.adapter
    }

    private fun navigateToAnimalDetail(animal: Animal) {
        val directions = AnimalListFragmentDirections.navigateToAnimalDetail(animal)
        findNavController().navigate(directions)
    }
}
