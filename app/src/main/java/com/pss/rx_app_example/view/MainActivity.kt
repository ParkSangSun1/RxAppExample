package com.pss.rx_app_example.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.pss.rx_app_example.R
import com.pss.rx_app_example.base.BaseActivity
import com.pss.rx_app_example.databinding.ActivityMainBinding
import com.pss.rx_app_example.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()


    override fun init() {
        val searchTextObservable = createButtonClickObservable()

        searchTextObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ query ->

                mainViewModel.getPapagoTranslationText(text = query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                        shortShowToast(it.message.result.translatedText)
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

}