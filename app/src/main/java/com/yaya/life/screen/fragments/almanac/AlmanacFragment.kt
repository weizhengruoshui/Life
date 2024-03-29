package com.yaya.life.screen.fragments.almanac

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.yaya.life.common.AppFragment
import com.yaya.life.common.viewmodel.DataViewModelFactory
import com.yaya.life.databinding.FragmentAlmanacBinding
import com.yaya.data.almanac.AlmanacDay
import com.yaya.data.almanac.AlmanacHour
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class AlmanacFragment :
    AppFragment<Pair<AlmanacDay?, MutableList<AlmanacHour>?>, AlmanacViewModel>() {

    @Inject
    lateinit var dataViewModelFactory: DataViewModelFactory

    private var _binding: FragmentAlmanacBinding? = null

    private val binding get() = _binding!!

    override fun onCreateRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlmanacBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun attributeViews() {
        //nothing to be done
    }

    override fun populateData(data: Pair<AlmanacDay?, MutableList<AlmanacHour>?>) {
        val almanacDay = data.first
        binding.tvAlmanacSolarCalendarValue.text = almanacDay?.yangli
        binding.tvAlmanacLunarCalendarValue.text = almanacDay?.yinli
        binding.tvAlmanacFiveElementsValue.text = almanacDay?.wuxing
        binding.tvAlmanacOffenseValue.text = almanacDay?.chongsha
        binding.tvAlmanacForbiddanceValue.text = almanacDay?.baiji
        binding.tvAlmanacWorshipValue.text = almanacDay?.xiongshen
        binding.tvAlmanacSacrificeOfGodsValue.text = almanacDay?.jishen
        binding.tvAlmanacBenefitsValue.text = almanacDay?.yi
        binding.tvAlmanacDrawbacksValue.text = almanacDay?.ji
    }

    override fun getViewModel(): AlmanacViewModel {
        return ViewModelProvider(
            this,
            dataViewModelFactory
        ).get(AlmanacViewModel::class.java)
    }

    override fun resetViewBinding() {
        _binding = null
    }
}