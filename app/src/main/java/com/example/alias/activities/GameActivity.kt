package com.example.alias.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.alias.R
import com.example.alias.storage.GameState
import com.example.alias.utils.AnswerWord
import com.example.alias.utils.OnSwipeTouchListener
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*
import java.util.concurrent.TimeUnit

@SuppressLint("SetTextI18n")
class GameActivity : AppCompatActivity() {
    private val answers = mutableListOf<AnswerWord>()

    private var guessedWords = 0
    private var skippedWords = 0
    private var wordsIterator = GameState.categoryWords.shuffled().iterator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val chronometer = Chronometer(
            TimeUnit.MILLISECONDS.convert(
                preferences.getInt("roundTime", 1).toLong(),
                TimeUnit.SECONDS
            )
        )

        gameButton.setOnClickListener {
            when (chronometer.state) {
                ChronometerState.OFF -> {
                    chronometer.start()
                    chronometer.state = ChronometerState.RUNNING
                    gameButton.text = TEXT_PAUSE
                    generateNextWord()
                }
                ChronometerState.RUNNING -> {
                    chronometer.pause()
                    chronometer.state = ChronometerState.PAUSED
                    gameButton.text = TEXT_RESUME
                    gameWordTextView.text = TEXT_PAUSE
                }
                ChronometerState.PAUSED -> {
                    chronometer.resume()
                    chronometer.state = ChronometerState.RUNNING
                    gameButton.text = TEXT_PAUSE
                    generateNextWord()
                }
                ChronometerState.FINISHED -> {
                }
            }
        }

        gameWordTextView.setOnTouchListener(object : OnSwipeTouchListener(this@GameActivity) {
            override fun onSwipeLeft() {
                when (chronometer.state) {
                    ChronometerState.RUNNING -> {
                        skippedTextView.text = "$TEXT_SKIPPED_WORDS: ${++skippedWords}"
                        answers.add(AnswerWord(gameWordTextView.text.toString(), false))
                        generateNextWord()
                    }
                    else -> return
                }
            }

            override fun onSwipeRight() {
                when (chronometer.state) {
                    ChronometerState.RUNNING -> {
                        guessedTextView.text = "$TEXT_GUESSED_WORDS : ${++guessedWords}"
                        answers.add(AnswerWord(gameWordTextView.text.toString(), true))
                        generateNextWord()
                    }
                    else -> return
                }
            }
        })
    }

    private fun generateNextWord() {
        if (wordsIterator.hasNext()) {
            gameWordTextView.text = wordsIterator.next().capitalize(Locale.ROOT)
        } else {
            wordsIterator = GameState.categoryWords.iterator()
        }
    }

    private inner class Chronometer(initialTimerTime: Long) {
        private val tickTime: Long = 1000L
        private var millisRemaining: Long = initialTimerTime

        private lateinit var timer: MyTimer

        var state = ChronometerState.OFF

        fun start() {
            timer = MyTimer(millisRemaining)
            timer.start()
        }

        fun pause() {
            timer.cancel()
        }

        fun resume() {
            start()
        }

        @SuppressLint("SetTextI18n")
        inner class MyTimer(initialMillisTime: Long) : CountDownTimer(
            initialMillisTime,
            tickTime
        ) {
            override fun onTick(millisUntilFinished: Long) {
                millisRemaining = millisUntilFinished
                val secondsRemaining = millisUntilFinished / 1000

                val minutesUntilFinished = secondsRemaining / 60
                val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60

                val secondsStr = secondsInMinuteUntilFinished.toString().let {
                    if (it.length == 2) it
                    else "0$it"
                }

                chronometerView.text = "$minutesUntilFinished:$secondsStr"
            }

            override fun onFinish() {
                state = ChronometerState.FINISHED
                gameButton.isEnabled = false
                chronometerView.text = TEXT_TIME_OUT

                val answerActivityIntent = Intent(
                    this@GameActivity,
                    AnswerActivity::class.java
                )
                answerActivityIntent.putExtra("data", ArrayList(answers))
                startActivity(answerActivityIntent)
            }
        }
    }

    enum class ChronometerState {
        FINISHED,
        RUNNING,
        PAUSED,
        OFF,
    }

    private companion object {
        private const val TEXT_GUESSED_WORDS = "Отгадано"
        private const val TEXT_SKIPPED_WORDS = "Пропущено"
        private const val TEXT_TIME_OUT = "Время вышло"
        private const val TEXT_RESUME = "Возобновить"
        private const val TEXT_PAUSE = "Пауза"
    }
}