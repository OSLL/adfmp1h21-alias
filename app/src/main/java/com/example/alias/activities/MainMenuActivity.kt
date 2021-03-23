package com.example.alias.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alias.R
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        startButton.setOnClickListener {
            val teamActivityIntent = Intent(this, TeamActivity::class.java)
            startActivity(teamActivityIntent)
        }

        rulesButton.setOnClickListener {
            val rulesActivityIntent = Intent(this, RulesActivity::class.java)
            startActivity(rulesActivityIntent)
        }

        settingsButton.setOnClickListener {
            val settingsActivityIntent = Intent(this, SettingsActivity::class.java)
            startActivity(settingsActivityIntent)
        }
    }
}