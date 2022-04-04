package com.pss.rx_app_example.data.remote.api

import com.pss.rx_app_example.data.remote.model.PapagoTranslationResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PapagoApi {
    @POST("/v1/papago/n2mt")
    fun papagoTranslation(
        @Header("X-Naver-Client-Id") X_Naver_Client_Id : String,
        @Header("X-Naver-Client-Secret") X_Naver_Client_Secret : String,
        @Body source : String,
        @Body target : String,
        @Body text : String
    ) : Single<PapagoTranslationResponse>
}