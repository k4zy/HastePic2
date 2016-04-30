package com.kazy.hastepic2.activity

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kazy.hastepic2.R
import com.kazy.hastepic2.databinding.ActivityMainBinding
import com.kazy.hastepic2.presenter.MainPresenter
import com.kazy.hastepic2.repository.LocalImageRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this as Activity, R.layout.activity_main)
        MainPresenter(binding, LocalImageRepository(this as Context))
    }
}
