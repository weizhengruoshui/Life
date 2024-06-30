package com.yaya.life.basic.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yaya.life.basic.AppActivity

abstract class AppFragment : Fragment() {

    lateinit var parentActivity: AppActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentActivity = requireActivity() as AppActivity
    }

    override fun onDestroyView() {
        super.onDestroyView()
        resetViewBinding()
    }

    abstract fun resetViewBinding()
}