package com.example.alias.activities

import android.os.Bundle
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alias.R
import com.example.alias.utils.OnSwipeTouchListener
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity() {
    private var counter = 10L
    private var isPaused = false
    private var isFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        chronometerCountDown.setOnChronometerTickListener {
            Chronometer.OnChronometerTickListener {
                if (counter < 0) {
                    // TODO: next intent
                    return@OnChronometerTickListener
                }

                chronometerCountDown.base = counter
                counter--

                Toast.makeText(
                    this@GameActivity,
                    counter.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        chronometerCountDown.base = counter
        chronometerCountDown.start()


        pauseButton.setOnClickListener {
            if (isPaused) {
                isPaused = false
                chronometerCountDown.base = counter
                chronometerCountDown.start()
                Toast.makeText(this@GameActivity, "start", Toast.LENGTH_SHORT).show()
            } else {
                isPaused = true
                chronometerCountDown.stop()
                Toast.makeText(this@GameActivity, "stop", Toast.LENGTH_SHORT).show()
            }
        }


        gameWordTextView.setOnTouchListener(object : OnSwipeTouchListener(this@GameActivity) {
            override fun onSwipeLeft() {
                Toast.makeText(this@GameActivity, "Left", Toast.LENGTH_SHORT).show()
            }

            override fun onSwipeRight() {
                Toast.makeText(this@GameActivity, "Right", Toast.LENGTH_SHORT).show()
            }
        })
    }

//    private fun onChronometerTickHandler() {
////        if (counter < 0) {
////            counter = 20L
////        }
//
//        chronometerCountDown.base = counter
//        counter--
//
//        Toast.makeText(this@GameActivity, counter.toString(), Toast.LENGTH_SHORT).show()
//    }

//    private fun timer(millisInFuture: Long): CountDownTimer {
//        return object : CountDownTimer(millisInFuture, 1_000) {
//            override fun onTick(millisUntilFinished: Long) {
////                val timeRemaining = "Seconds remaining\n${millisUntilFinished / 1000}"
//
//                if (isPaused) {
////                    editTextTime.setText("${millisUntilFinished / 1000}")
//                    resumeTime = millisUntilFinished
//                    cancel()
//
////                    text_view.text = "${text_view.text}\nPaused"
//                    // To ensure start timer from paused time
////                } else if (isCancelled) {
////                    text_view.text = "${text_view.text}\nStopped.(Cancelled)"
////                    cancel()
//                } else {
////                    text_view.text = timeRemaining
//                }
//            }
//
//            override fun onFinish() {
////                text_view.text = "Done"
//            }
//        }
//    }
}