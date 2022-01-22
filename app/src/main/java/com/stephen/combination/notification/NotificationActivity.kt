package com.stephen.combination.notification

import androidx.lifecycle.ViewModelProvider
import com.stephen.combination.databinding.ActivityNotificationBinding
import com.stephen.combination.common.AppActivity

class NotificationActivity : AppActivity<String, NotificationViewModel>() {

    lateinit var binding: ActivityNotificationBinding

    override fun initVariables() {
        //Nothing to do.
    }

    override fun attributeViews() {
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun populateData(data: String) {
        binding.fromWhere.text = data
    }

    override fun getViewModel(): NotificationViewModel {
        return ViewModelProvider(this).get(NotificationViewModel::class.java)
    }
}