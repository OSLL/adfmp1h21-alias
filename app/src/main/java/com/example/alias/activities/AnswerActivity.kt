package com.example.alias.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.alias.R
import com.example.alias.storage.GameState
import kotlinx.android.synthetic.main.activity_answer.*
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AnswerActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        // TODO: list of answers with switch button
//        val rawData = intent.getSerializableExtra("data")
//        val data = Json.decodeFromString<List<GameActivity.AnswerWord>>(rawData)

//        val answerAdapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            data
//        )
//
//        answerListView.apply {
//            adapter = answerAdapter
//        }

        // TODO: fix
        val points = 5

        // TODO: logic for preview
        nextButton.setOnClickListener {
            val teams = GameState.teamRating.keys.toList()
            val currentTeam = GameState.teamCounter % GameState.teamRating.size
            val team = teams[currentTeam]

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