package com.example.alias.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.alias.R
import com.example.alias.storage.GameState
import kotlinx.android.synthetic.main.activity_game_preview.*
import kotlinx.android.synthetic.main.activity_team.*
import kotlinx.android.synthetic.main.activity_team.letsPlayButton

class TeamActivity : AppCompatActivity() {
    private val teams = mutableListOf<String>(
        "Team1", "Team2" // FIXME: hardcode data
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val teamAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, teams)

        teamListView.apply {
            adapter = teamAdapter
        }

        addTeamButton.setOnClickListener {
            val newTeam = teamTextView.text.toString()
            teams.add(newTeam)

            teamAdapter.notifyDataSetChanged()
        }

        letsPlayButton.setOnClickListener {
            GameState.teams.addAll(teams) // TODO: убрать?

            teams.forEach {
                GameState.teamRating[it] = 0
            }

            val categoryActivityIntent = Intent(this, CategoryActivity::class.java)
            startActivity(categoryActivityIntent)
        }

    }
}