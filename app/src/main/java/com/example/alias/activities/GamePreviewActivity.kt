package com.example.alias.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.alias.R
import com.example.alias.storage.GameState
import kotlinx.android.synthetic.main.activity_game_preview.*

class GamePreviewActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_preview)

        val teams = GameState.teamRating.keys.toList()
        val currentTeam = GameState.teamCounter % GameState.teamRating.size
        val currentRound = (GameState.roundCounter) / GameState.teamRating.size + 1

        currentTeamTextView.text = "Команда: ${teams[currentTeam]}"
        currentRoundTextView.text = "Раунд: $currentRound"

        val teamRatingAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            GameState.teamRating.map { "${it.key}: ${it.value}" }
        )

        teamRatingListView.apply {
            adapter = teamRatingAdapter
        }

        teamRatingAdapter.notifyDataSetChanged()

        letsPlayButton.setOnClickListener {
            val gameActivityIntent = Intent(this, GameActivity::class.java)
            startActivity(gameActivityIntent)
        }
    }
}