package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.activityViewModels
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.R
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.databinding.FragmentAttractionDetailBinding
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.AttractionsViewModel
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.base.BaseFragment
import timber.log.Timber


class AttractionDetailFragment : BaseFragment<FragmentAttractionDetailBinding>() {

    private val attraction by lazy { sharedViewModel.currentAttraction }
    override fun getViewBinding(): FragmentAttractionDetailBinding =
        FragmentAttractionDetailBinding.inflate(layoutInflater)

    override fun setupView() {
        super.setupView()
        attraction?.let {
            with(binding) {
                txtAttractionName.text = it.name
                txtAttractionDescription.text = it.introduction
                txtAttractionUrl.text = it.url
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupListener() {
        super.setupListener()
        binding.txtAttractionUrl.setOnClickListener {
            Timber.d("ToanClicked: url: ${attraction?.url}")
            binding.attractionWebView.apply {
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()
                settings.javaScriptEnabled = true
                attraction?.let { att -> loadUrl(att.url) }
            }
        }
    }
}