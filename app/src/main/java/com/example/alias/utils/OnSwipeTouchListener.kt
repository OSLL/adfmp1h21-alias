package com.example.alias.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.abs

open class OnSwipeTouchListener(context: Context) : OnTouchListener {
    private val gestureDetector: GestureDetector = GestureDetector(context, GestureListener())

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    open fun onSwipeRight() {}

    open fun onSwipeLeft() {}

    open fun onSwipeTop() {}

    open fun onSwipeBottom() {}

    private inner class GestureListener : SimpleOnGestureListener() {
        override fun onDown(event: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            event1: MotionEvent, event2: MotionEvent,
            velocityX: Float, velocityY: Float
        ): Boolean {
            var result = false
            try {
                val diffY = event2.y - event1.y
                val diffX = event2.x - event1.x

                if (abs(diffX) > abs(diffY)) {
//                    abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD
//                    abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD
                    if (predicate(diffX, velocityX)) {
                        if (diffX > 0) onSwipeRight()
                        else onSwipeLeft()
                        result = true
                    }
                } else if (predicate(diffY, velocityY)) {
                    if (diffY > 0) onSwipeBottom()
                    else onSwipeTop()
                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return result
        }

        private fun predicate(diff: Float, velocity: Float): Boolean {
            return abs(diff) > SWIPE_THRESHOLD && abs(velocity) > SWIPE_VELOCITY_THRESHOLD
        }
    }

    private companion object {
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100
    }
}