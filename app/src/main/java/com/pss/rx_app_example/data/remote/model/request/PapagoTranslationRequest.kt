package com.pss.rx_app_example.data.remote.model.request

import retrofit2.http.Body

data class PapagoTranslationRequest(
    val source: String,
    val target: String,
    val text: String
)
