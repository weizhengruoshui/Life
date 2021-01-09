package com.stephen.combination.screen.fragments.almanac

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.stephen.combination.R
import com.yaya.utils.animation.PropertyAnimatorUtils
import com.stephen.combination.common.viewmodel.DataViewModelFactory
import com.yaya.data.almanac.AlmanacDay
import com.yaya.data.almanac.AlmanacHour
import com.stephen.combination.databinding.FragmentAlmanacBinding
import com.stephen.combination.common.PageFragment
import javax.inject.Inject

class AlmanacFragment :
    PageFragment<Pair<AlmanacDay?, MutableList<AlmanacHour>?>, AlmanacViewModel>() {

    @Inject
    lateinit var dataViewModelFactory: DataViewModelFactory

    private var _binding: FragmentAlmanacBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance(): AlmanacFragment {
            val args = Bundle()
            val fragment =
                AlmanacFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initVariables(savedInstanceState: Bundle?) {
        super.initVariables(savedInstanceState)
        provideFragmentComponent().inject(this)
    }

    override fun onCreateRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlmanacBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun attributeViews() {
        binding.almanacRootView.apply {
            setBackgroundResource(R.drawable.alpha_background)
            setOnClickListener {
                (background as AnimationDrawable).stop()
                (background as AnimationDrawable).start()
                PropertyAnimatorUtils.alphaAnimator(binding.almanacCoverView, 0f, 1.0f, 1000)
            }
        }

        binding.almanacAnimationButton.apply {
            setOnClickListener {
                val myAnimation = AnimationUtils.loadAnimation(context, R.anim.animation_set)
                startAnimation(myAnimation)
                PropertyAnimatorUtils.valueAnimator()
            }
        }
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