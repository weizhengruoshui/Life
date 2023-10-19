package com.yaya.life.screen.fragments.bottomsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yaya.life.common.manager.AppActivityRouter
import com.yaya.life.databinding.FragmentSettingsBottomSheetBinding
import com.yaya.life.notification.NotificationData
import com.yaya.life.notification.RegularNotificationFactory
import com.yaya.life.notification.WithParentNotification
import com.yaya.life.screen.account.AccountActivity
import javax.inject.Inject

class SettingsBottomSheetFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var regularNotificationFactory: RegularNotificationFactory

    @Inject
    lateinit var withParentNotification: WithParentNotification

    private var _binding: FragmentSettingsBottomSheetBinding? = null

    // This property is only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    companion object {
        const val TAG = "SettingsBottomSheetFragment"
        fun newInstance(): SettingsBottomSheetFragment {
            return SettingsBottomSheetFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profile.apply {
            setOnClickListener {
                AppActivityRouter.startActivity(activity, AccountActivity::class.java)
                dismiss()
            }
        }

        binding.regularNotification.apply {
            setOnClickListener {
                regularNotificationFactory.showNotification(
                    context,
                    NotificationData.REGULAR_CHANNEL_ID,
                    1
                )
                dismiss()
            }
        }

        binding.withParentNotification.apply {
            setOnClickListener {
                withParentNotification.showNotification(
                    context,
                    NotificationData.WITH_PARENT_CHANNEL_ID,
                    2
                )
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}