package com.techlads.webviewexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techlads.webviewexample.network.AssetResponse
import com.techlads.webviewexample.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class AssetViewModel(val repository: AssetsRepository): ViewModel() {

    private val assets = MutableLiveData<Resource<AssetResponse>>()
    val assetResponse:LiveData<Resource<AssetResponse>> = assets

    fun getModels() {
        viewModelScope.launch {
            repository.getModels().collect {
                assets.value = it
            }
        }
    }
}