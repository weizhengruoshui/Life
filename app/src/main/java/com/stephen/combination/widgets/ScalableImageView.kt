package com.stephen.combination.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView
import com.yaya.utils.LogUtils

class ScalableImageView(context: Context, attrs: AttributeSet? = null) :
    AppCompatImageView(context, attrs) {

    private var scaleGestureDetector: ScaleGestureDetector =
        ScaleGestureDetector(context, ScaleListener())
    private var mScaleFactor = 1f
    private var invalidPointerId = -1
    private var primaryTouchX = 0f
    private var primaryTouchY = 0f
    private var secondaryTouchX = 0f
    private var secondaryTouchY = 0f
    private var activePointerId = 0
    private var checkScaleFactor = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return super.onTouchEvent(event)
        }

        // scale event
        scaleGestureDetector.onTouchEvent(event)

        //drag event
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                checkScaleFactor = false
                event.actionIndex.also { primaryIndex ->
                    //Remember where we started (for dragging)
                    primaryTouchX = event.getX(primaryIndex)
                    primaryTouchY = event.getY(primaryIndex)
                    activePointerId = event.getPointerId(primaryIndex)
                }
                LogUtils.logD(javaClass.simpleName, "down")
            }
            MotionEvent.ACTION_MOVE -> {
                // Find the index of the active pointer and fetch its position
                val (x: Float, y: Float) = event.findPointerIndex(activePointerId)
                    .let { activeIndex ->
                        event.getX(activeIndex) to event.getY(activeIndex)
                    }

                for (i in 0 until event.pointerCount) {
                    if (i == 0) {
                        val primaryPointX = event.getX(i)
                        val primaryPointY = event.getY(i)
                        LogUtils.logD(
                            javaClass.simpleName,
                            "primaryPoint: ($primaryPointX, $primaryPointY) + pointId: ${event.getPointerId(
                                i
                            )}"
                        )
                    }

                    if (i == 1) {
                        val anotherPointX = event.getX(i)
                        val anotherPointY = event.getY(i)
                        LogUtils.logD(
                            javaClass.simpleName,
                            "notPrimaryPoint: ($anotherPointX, $anotherPointY) + pointId: ${event.getPointerId(
                                i
                            )}"
                        )
                    }
                }

                if (event.pointerCount <= 1)
                    scrollBy((primaryTouchX - x).toInt(), (primaryTouchY - y).toInt())
                primaryTouchX = x
                primaryTouchY = y
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                activePointerId = invalidPointerId
                checkScaleFactor = true
                invalidate()
                LogUtils.logD(javaClass.simpleName, "up")
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                event.actionIndex.let { secondaryIndex ->
                    secondaryTouchX = event.getX(secondaryIndex)
                    secondaryTouchY = event.getY(secondaryIndex)
                }
                LogUtils.logD(javaClass.simpleName, "pointer down")
            }
            MotionEvent.ACTION_POINTER_UP -> {
                event.actionIndex.let { secondaryIndex ->
                    event.getPointerId(secondaryIndex)
                        .takeIf { it == activePointerId }
                        ?.let {
                            // this was our active pointer going up. Choose a new active pointer
                            // and adjust accordingly
                            val newPrimaryIndex = if (secondaryIndex == 0) 1 else 0
                            primaryTouchX = event.getX(newPrimaryIndex)
                            primaryTouchY = event.getY(newPrimaryIndex)
                            activePointerId = event.getPointerId(newPrimaryIndex)
                        }
                }
                checkScaleFactor = true
                invalidate()
                LogUtils.logD(javaClass.simpleName, "pointer up")
            }

        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        pivotX = 0f
        pivotY = 0f
        canvas.run {
            save()
            if (mScaleFactor <= 1f && checkScaleFactor)
                resetPosition()
            scale(mScaleFactor, mScaleFactor)
            super.onDraw(canvas)
            restore()
        }
    }

    private fun resetPosition() {
        mScaleFactor = 1f
        scrollBy(-scrollX, -scrollY)
    }

    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            mScaleFactor *= detector.scaleFactor

            // Don't let the object get too small or too large.
            mScaleFactor = 0.1f.coerceAtLeast(mScaleFactor.coerceAtMost(5.0f))

            invalidate()
            return true
        }
    }
}