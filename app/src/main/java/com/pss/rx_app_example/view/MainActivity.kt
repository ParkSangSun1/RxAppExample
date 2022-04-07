package com.pss.rx_app_example.view

import androidx.activity.viewModels
import com.pss.rx_app_example.R
import com.pss.rx_app_example.base.BaseActivity
import com.pss.rx_app_example.databinding.ActivityMainBinding
import com.pss.rx_app_example.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()
    lateinit var translationAfterTextObservable: Disposable
    lateinit var translationTextObservable: Observable<String>


    override fun init() {
        observableSubscribe()
    }

    private fun observableSubscribe() {
        translationTextObservable = createButtonClickObservable()

        translationTextObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ query ->

                mainViewModel.getPapagoTranslationText(text = query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response ->

                        translationAfterTextObservable =
                            createTranslationObservable(response.message.result.translatedText)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({ text ->

                                    binding.translationAfterText.text = text

                                }, {

                                    binding.translationAfterText.text = "오류가 발생했습니다"

                                })

                    }, { error ->

                        shortShowToast(error.toString())

                    })
            }, { error ->

                shortShowToast(error.toString())

            })
    }

    private fun createButtonClickObservable(): Observable<String> {

        return Observable.create { emitter ->

            binding.searchBtn.setOnClickListener {
                emitter.onNext(binding.searchEditTxt.text.toString())
            }

            emitter.setCancellable {
                binding.searchBtn.setOnClickListener(null)
            }

        }
    }

    private fun createTranslationObservable(text: String): Observable<String> {
        return Observable.create { emitter ->
            emitter.onNext(text)
        }
    }
}