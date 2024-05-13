package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.detail

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.PageTransformer
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.R
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.databinding.FragmentAttractionDetailBinding
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.base.BaseFragment
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.detail.animation.HorizontalMarginItemDecoration
import kotlin.math.abs


class AttractionDetailFragment : BaseFragment<FragmentAttractionDetailBinding>() {

    private lateinit var photoAdapter: AttractionDetailPhotosAdapter

    private val attraction by lazy { sharedViewModel.currentAttraction }
    override fun getViewBinding(): FragmentAttractionDetailBinding =
        FragmentAttractionDetailBinding.inflate(layoutInflater)

    override fun setupView() {
        super.setupView()
        attraction?.let {
            with(binding) {
                attractionDetailTitleArea.apply {
                    topContentName.text = it.name
                    topContentName.visibility = View.VISIBLE
                    topBtnBack.visibility = View.VISIBLE
                    ivSelectedCountry.visibility = View.GONE
                    ivLogo.visibility = View.GONE
                }

                txtAttractionName.text = it.name
                txtAttractionDescription.text = it.introduction

                photoAdapter = AttractionDetailPhotosAdapter(it.images)
                photosPager.apply {
                    adapter = photoAdapter
                    offscreenPageLimit = 1

                    val nextItemVisiblePx =
                        resources.getDimension(R.dimen.viewpager_next_item_visible)
                    val currentItemHorizontalMarginPx =
                        resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
                    val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
                    val pageTransformer = PageTransformer { page: View, position: Float ->
                        page.translationX = -pageTranslationX * position
                        page.scaleY = 1 - (0.25f * abs(position))
                    }
                    setPageTransformer(pageTransformer)
                    val itemDecoration = HorizontalMarginItemDecoration(
                        context,
                        R.dimen.viewpager_current_item_horizontal_margin
                    )
                    addItemDecoration(itemDecoration)
                }

                attractionDetailTitleArea.icBtnBack.setOnClickListener {
                    if (attractionWebView.visibility == View.VISIBLE) {
                        attractionWebView.visibility = View.GONE
                        binding.fabAttractDetails.text = "Detail"
                    } else {
                        sharedViewModel.currentAttraction = null
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun setupListener() {
        super.setupListener()
        binding.fabAttractDetails.setOnClickListener {
            if (binding.attractionWebView.visibility != View.VISIBLE) {
                binding.attractionWebView.apply {
                    visibility = View.VISIBLE
                    webViewClient = WebViewClient()
                    webChromeClient = WebChromeClient()
                    settings.javaScriptEnabled = true
                    attraction?.let { att -> loadUrl(att.url) }
                }
                binding.fabAttractDetails.text = "Back"
            } else {
                binding.fabAttractDetails.text = "Detail"
                binding.attractionWebView.visibility = View.GONE
            }
        }
    }
}