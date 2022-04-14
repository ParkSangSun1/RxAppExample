package com.pss.rx_app_example.viewmodel

import androidx.lifecycle.ViewModel
import com.pss.rx_app_example.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    //true : 영어 -> 한국어 (기본), false : 한국어 -> 영어
    var language = true


    fun getPapagoTranslationText(text : String, source : String, target : String) = mainRepository.getPapagoTranslationText(text = text, source = source, target = target)
}