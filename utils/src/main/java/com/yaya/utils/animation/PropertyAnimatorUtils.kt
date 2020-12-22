package com.yaya.utils.animation

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View

class PropertyAnimatorUtils {

    companion object {
        fun alphaAnimator(view: View, start: Float, end: Float, duration: Long) {
            ObjectAnimator.ofFloat(view, "alpha", start, end)
                .apply {
                    this.duration = duration
                    start()
                }
        }

        fun valueAnimator() {
            val valueAnimator = ValueAnimator.ofFloat(0f, 5f, 3f, 10f)
            valueAnimator.duration = 5000
            valueAnimator.startDelay = 0
            valueAnimator.repeatCount = 0
            valueAnimator.repeatMode = ValueAnimator.RESTART
            /*
            ValueAnimator.START:
            ValueAnimator.REVERSE:
             */
            valueAnimator.addUpdateListener { animation ->
                val currentValue = animation.animatedValue
                com.yaya.utils.LogUtils.logD(
                    PropertyAnimatorUtils::class.java.simpleName,
                    "onAnimationUpdate: $currentValue"
                )
            }
            valueAnimator.start()
        }
    }
}