package com.example.alias.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alias.R
import com.example.alias.storage.GameState
import com.example.alias.utils.AnswerWord
import com.example.alias.utils.SwitchRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_answer.*
import kotlin.properties.Delegates

class AnswerActivity : AppCompatActivity(), SwitchRecyclerViewAdapter.OnItemClickListener {
    private lateinit var answerWords: List<AnswerWord>
    private lateinit var switchRecyclerViewAdapter: SwitchRecyclerViewAdapter

    private var wordsCountToWin by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        answerWords = intent.getParcelableArrayListExtra("data")
        switchRecyclerViewAdapter = SwitchRecyclerViewAdapter(
            answerWords,
            this
        )

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val penalty = preferences.getBoolean("wrongPenalty", false)
        wordsCountToWin = preferences.getInt("wordCount", 1)

        answerRecyclerListView.apply {
            adapter = switchRecyclerViewAdapter
            layoutManager = LinearLayoutManager(this@AnswerActivity)
            setHasFixedSize(true)
        }

        nextButton.setOnClickListener {
            calculateResult(answerWords, penalty)

            val teams = GameState.teamRating.keys.toList()
            val currentTeam = GameState.teamCounter % GameState.teamRating.size
            val team = teams[currentTeam]

            if (GameState.teamRating[team]!! >= wordsCountToWin) {
                GameState.winner = team
                val recordActivity = Intent(this, RecordActivity::class.java)
                startActivity(recordActivity)
                return@setOnClickListener
            }

            GameState.teamCounter++
            GameState.roundCounter++

            val gamePreviewActivityIntent = Intent(this, GamePreviewActivity::class.java)
            startActivity(gamePreviewActivityIntent)
        }
    }

    override fun onItemClick(position: Int) {
        val clickedItem = answerWords[position]
        clickedItem.isGuessed = !clickedItem.isGuessed

        switchRecyclerViewAdapter.notifyItemChanged(position)
    }

    fun calculateResult(answerWords: List<AnswerWord>, withPenalty: Boolean) {
        val teams = GameState.teamRating.keys.toList()
        val currentTeam = GameState.teamCounter % GameState.teamRating.size
        val team = teams[currentTeam]
        val answerStatus = answerWords.map { it.isGuessed }
        var points = answerStatus
            .filter { it }
            .count()

        if (withPenalty) {
            points -= answerStatus
                .filterNot { it }
                .count()
        }

        GameState.teamRating[team] = GameState.teamRating.getOrDefault(team, 0) + points
    }
}