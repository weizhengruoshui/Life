package com.yaya.life.screen.account

import androidx.lifecycle.ViewModelProvider
import com.yaya.life.R
import com.yaya.life.common.AppActivity
import com.yaya.life.databinding.ActivityAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountActivity : AppActivity<String, AccountViewModel>() {

    private lateinit var binding: ActivityAccountBinding

    override fun attributeViews() {
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.accountTopBar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.account)
    }

    override fun populateData(data: String) {
        binding.accountTxt.text = getString(R.string.long_text_example)
    }

    override fun getViewModel(): AccountViewModel {
        return ViewModelProvider(this)[AccountViewModel::class.java]
    }
}