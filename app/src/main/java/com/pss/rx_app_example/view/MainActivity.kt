package com.pss.rx_app_example.view

import androidx.activity.viewModels
import com.jakewharton.rxbinding3.view.clicks
import com.pss.rx_app_example.R
import com.pss.rx_app_example.base.BaseActivity
import com.pss.rx_app_example.data.remote.model.delivery.TranslationDataDelivery
import com.pss.rx_app_example.databinding.ActivityMainBinding
import com.pss.rx_app_example.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()
    private val compositeDisposable = io.reactivex.disposables.CompositeDisposable()
    private val compositeRx3Disposable = io.reactivex.rxjava3.disposables.CompositeDisposable()


    override fun init() {
        changeTranslationLanguageClickEvent()
        translationButtonClickEvent()
    }

    private fun translationButtonClickEvent() {

        val translationClickObservable = Observable.create<TranslationDataDelivery> { emitter ->
            binding.translationBtn.setOnClickListener {
                emitter.onNext(TranslationDataDelivery(binding.searchEditTxt.text.toString(), mainViewModel.language))
            }

            emitter.setCancellable {
                binding.translationBtn.setOnClickListener(null)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ data ->
                val source = if (data.languageState) "en" else "ko"
                val target = if (data.languageState) "ko" else "en"
                val papagoTranslationObservable = mainViewModel.getPapagoTranslationText(text = data.text, source = source, target = target)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->
                        binding.translationAfterText.text = response.message.result.translatedText
                    }, { error ->
                        shortShowToast(error.toString())
                    })
                compositeRx3Disposable.add(papagoTranslationObservable)
            }, { error ->

                shortShowToast(error.toString())

            })
        compositeRx3Disposable.add(translationClickObservable)

    }

    private fun changeTranslationLanguageClickEvent(){
       val changeTranslationLanguageClickObservable = binding.change.clicks()
           .subscribe {
               mainViewModel.language = !mainViewModel.language
               if (mainViewModel.language){
                   binding.beforeTxt.text = "영어"
                   binding.afterTxt.text = "한국어"
               }else{
                   binding.beforeTxt.text = "한국어"
                   binding.afterTxt.text = "영어"
               }
           }
        compositeDisposable.add(changeTranslationLanguageClickObservable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
        compositeRx3Disposable.dispose()
    }
}