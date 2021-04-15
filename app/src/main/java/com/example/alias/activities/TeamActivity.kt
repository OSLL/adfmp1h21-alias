package com.example.alias.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alias.R
import com.example.alias.storage.GameState
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        var teamCounter = 2

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

            if (newTeam.isEmpty()) {
                return@setOnClickListener
            }

            if (!GameState.teamRating.containsKey(newTeam)) {
                GameState.teamRating[newTeam] = 0
                teams.add(newTeam)

                teamTextView.setText("Team${teamCounter++}")
            }

            teamAdapter.notifyDataSetChanged()
        }

        letsPlayButton.setOnClickListener {
            if (teams.isEmpty()) {
                Toast.makeText(this, "Необходимо добавить команду", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val categoryActivityIntent = Intent(this, CategoryActivity::class.java)
            startActivity(categoryActivityIntent)
        }
    }
}