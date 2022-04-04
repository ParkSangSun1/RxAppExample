package com.pss.rx_app_example.view

import androidx.activity.viewModels
import com.pss.quick_setup.R
import com.pss.quick_setup.databinding.ActivityMainBinding
import com.pss.rx_app_example.base.BaseActivity
import com.pss.rx_app_example.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()


    override fun init() {

    }

}