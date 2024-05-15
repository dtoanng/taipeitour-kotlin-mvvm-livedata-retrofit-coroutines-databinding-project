package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.AttractionsViewModel
import kotlinx.coroutines.launch

abstract class BaseFragment<VBinding : ViewBinding> : Fragment() {
    protected lateinit var binding: VBinding
    protected abstract fun getViewBinding(): VBinding
    protected val sharedViewModel: AttractionsViewModel by activityViewModels()

    open fun prepareData() {
        context?.let { sharedViewModel.checkInternetConnection(it) }
        /** calling api here*/
    }

    open fun setupView() {
        /** set up view here*/
    }

    open fun setupListener() {
        /** set up listener here*/
    }

    open fun observeData() {
        /** observe data from calling api here*/

        // observe network connection
        lifecycleScope.launch {
            sharedViewModel.networkState.collect { status ->
                if (status) {
                    Toast.makeText(context, "Connected...", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "No internet connection...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    open fun recreate() {
        /** recreate views or screen*/
    }

    private fun init() {
        binding = getViewBinding()
        /** declare viewmodels or somethings */
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        prepareData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeData()
        setupListener()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        /** recreate views or screen*/
        recreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}