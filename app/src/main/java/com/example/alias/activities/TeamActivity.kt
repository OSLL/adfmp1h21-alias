package com.example.alias.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.alias.R
import com.example.alias.storage.GameState
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val teams = GameState.teamRating.keys.toMutableList()
        val teamAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            teams
        )

        teamListView.apply {
            adapter = teamAdapter
        }

        addTeamButton.setOnClickListener {
            val newTeam = teamTextView.text.toString()
            if (!GameState.teamRating.containsKey(newTeam)) {
                GameState.teamRating[newTeam] = 0
                teams.add(newTeam)
            }

            teamAdapter.notifyDataSetChanged()
        }

        letsPlayButton.setOnClickListener {
            val categoryActivityIntent = Intent(this, CategoryActivity::class.java)
            startActivity(categoryActivityIntent)
        }
    }
}