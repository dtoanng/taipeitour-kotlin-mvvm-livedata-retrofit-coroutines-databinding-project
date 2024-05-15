package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AttractionsActivity : AppCompatActivity() {

    private lateinit var mainViewModel: AttractionsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[AttractionsViewModel::class]
        observeNetworkConnection()
        setContentView(R.layout.activity_home)
    }

    private fun observeNetworkConnection() {
        this.let { mainViewModel.checkInternetConnection(it) }
        lifecycleScope.launch {
            mainViewModel.networkState.collect { status ->
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Your internet is ${if (status) "connected!" else "disconnected!"}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}