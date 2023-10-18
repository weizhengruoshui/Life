package com.stephen.combination.screen.account

import androidx.lifecycle.ViewModelProvider
import com.stephen.combination.R
import com.stephen.combination.common.AppActivity
import com.stephen.combination.databinding.ActivityAccountBinding
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