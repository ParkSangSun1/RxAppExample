package com.pss.rx_app_example.repository

import com.pss.rx_app_example.data.remote.datasource.MainDataSource
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mainDataSource: MainDataSource
){
    fun getPapagoTranslationText(text : String, source : String, target : String) = mainDataSource.getPapagoTranslationText(text = text, source = source, target = target)
}