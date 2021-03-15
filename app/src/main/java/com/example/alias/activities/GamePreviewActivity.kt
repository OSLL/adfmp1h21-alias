package com.example.alias.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alias.R
import com.example.alias.storage.GameState
import kotlinx.android.synthetic.main.activity_game_preview.*

class GamePreviewActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_preview)

        currentTeamTextView.text = "Команда: ${GameState.teamCounter}"
        currentRoundTextView.text = "Раунд: ${GameState.roundCounter}"

        val teamRatingAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            GameState.teamRating.map { "${it.key}: ${it.value}" }
        )

        teamRatingListView.apply {
            adapter = teamRatingAdapter
        }

        teamRatingAdapter.notifyDataSetChanged()

//        updateButton.setOnClickListener {
//        }

        letsPlayButton.setOnClickListener {
            val gameActivityIntent = Intent(this, GameActivity::class.java)
            startActivity(gameActivityIntent)
        }
    }
}