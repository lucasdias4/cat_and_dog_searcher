package com.lucasdias.base.presentation

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.facebook.shimmer.ShimmerFrameLayout
import com.lucasdias.core.connectivity.Connectivity
import com.lucasdias.core.di.CONNECTIVITY
import com.lucasdias.extensions.animateGoneToVisible
import com.lucasdias.extensions.animateVisibleToGone
import com.lucasdias.extensions.showConnectivitySnackbar
import com.lucasdias.extensions.visible
import com.lucasdias.ui_components.error.ErrorViewComponent
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

abstract class BaseFragment<T : Any>(
    @IdRes private val successViewId: Int,
    @IdRes private val loadingViewId: Int,
    @IdRes private val errorViewId: Int,
    @LayoutRes private val fragmentLayoutId: Int
) : Fragment(fragmentLayoutId) {

    protected abstract val viewModel: BaseViewModel<T>
    protected lateinit var successView: ViewGroup
    protected lateinit var errorView: ErrorViewComponent
    protected lateinit var loadingView: ShimmerFrameLayout
    private val connectivity by inject<Connectivity>(named(CONNECTIVITY))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        connectivitySetup()
        setupErrorView(view)
        setupSuccessView(view)
        setupLoadingView(view)
        viewModel.executeRequest()
    }

    private fun setupSuccessView(view: View) {
        successView = view.findViewById(successViewId)
    }

    private fun setupErrorView(view: View) {
        errorView = view.findViewById(errorViewId)
        errorView.button.onComponentClickListener {
            viewModel.executeRequest()
        }
    }

    private fun setupLoadingView(view: View) {
        loadingView = view.findViewById(loadingViewId)
    }

    private fun connectivitySetup() {
        connectivity.getLiveData().observe(viewLifecycleOwner, Observer { hasNetworkConnectivity ->
            viewModel.updateConnectivityStatus(hasNetworkConnectivity)
            view?.showConnectivitySnackbar(hasNetworkConnectivity)
        })
    }

    open fun setupBackButton(action: () -> Unit) {
        val backButtonCallback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    action()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), backButtonCallback)
    }

    open fun onLoading() {
        if (errorView.visibility == View.VISIBLE) {
            errorView.button.isLoading = true
        } else {
            errorView.animateVisibleToGone()
        }

        if (viewModel.isInitialRequest() && errorView.visibility != View.VISIBLE) {
            loadingView.visible()
            successView.animateVisibleToGone()
        }
    }

    open fun onSuccess(model: Any?) {
        errorView.button.isLoading = false

        successView.animateGoneToVisible()
        loadingView.animateVisibleToGone()
        errorView.animateVisibleToGone()
    }

    open fun onSuccessWithoutContent() {
        Toast.makeText(requireContext(), "onSuccessWithoutContent", Toast.LENGTH_LONG).show()
    }

    open fun onError(throwable: Throwable?) {
        if (errorView.visibility == View.VISIBLE) {
            errorView.button.isLoading = false
        } else if (viewModel.isInitialRequest()) {
            errorView.animateGoneToVisible()
            successView.animateVisibleToGone()
            loadingView.animateVisibleToGone()
        }
    }
}
