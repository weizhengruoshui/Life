package com.yaya.life.notification

import androidx.lifecycle.ViewModelProvider
import com.yaya.life.databinding.ActivityNotificationBinding
import com.yaya.life.common.AppActivity

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