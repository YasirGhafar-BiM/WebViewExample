package com.techlads.webviewexample.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techlads.webviewexample.AssetViewModel
import com.techlads.webviewexample.AssetsRepository

class ViewModelFactory(val repository: AssetsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AssetViewModel::class.java)) {
            AssetViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}