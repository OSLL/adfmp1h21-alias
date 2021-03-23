package com.example.alias.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.alias.R
import com.example.alias.storage.GameState
import com.example.alias.utils.AnswerWord
import kotlinx.android.synthetic.main.activity_answer.*

class AnswerActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val data = intent.getParcelableArrayListExtra<AnswerWord>("data")
        val answerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            data
        )

        answerListView.apply {
            adapter = answerAdapter
        }

        nextButton.setOnClickListener {
            val teams = GameState.teamRating.keys.toList()
            val currentTeam = GameState.teamCounter % GameState.teamRating.size
            val team = teams[currentTeam]
            val points = data.map { it.isGuessed }.filter { it }.count()

            GameState.teamRating[team] = GameState.teamRating.getOrDefault(team, 0) + points
            GameState.teamCounter++
            GameState.roundCounter++

            val gamePreviewActivityIntent = Intent(
                this,
                GamePreviewActivity::class.java
            )
            startActivity(gamePreviewActivityIntent)
        }
    }
}