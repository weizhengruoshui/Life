package com.yaya.life.screen.bottomsheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yaya.life.databinding.FragmentSettingsBottomSheetBinding

class SettingsBottomSheetFragment : BottomSheetDialogFragment() {


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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}