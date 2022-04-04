package com.pss.rx_app_example.viewmodel

import androidx.lifecycle.ViewModel
import com.pss.rx_app_example.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

}