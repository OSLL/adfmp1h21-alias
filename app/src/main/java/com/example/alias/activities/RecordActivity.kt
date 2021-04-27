package com.example.alias.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.alias.R
import com.example.alias.storage.GameState
import kotlinx.android.synthetic.main.activity_game_preview.*
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        val teamRatingAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            GameState.teamRating.toList()
                .sortedByDescending { it.second }
                .toMap()
                .map { "${it.key}: ${it.value}" }
        )

        winnerTeamTextView.text = GameState.winner
        teamRecords.apply {
            adapter = teamRatingAdapter
        }
        teamRatingAdapter.notifyDataSetChanged()

        mainMenuButton.setOnClickListener {
            GameState.teamRating.clear()

            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }
    }
}