package com.pss.rx_app_example.data.remote.datasource

import com.pss.rx_app_example.data.remote.api.PapagoApi
import com.pss.rx_app_example.widget.utils.Utils
import javax.inject.Inject

class MainDataSource @Inject constructor(
    private val papagoApi: PapagoApi
) {
    fun getPapagoTranslationText(text : String) = papagoApi.papagoTranslation(Utils.X_NAVER_CLIENT_ID, Utils.X_NAVER_CLIENT_SECRET, source = "ko", target = "en", text = text)
}