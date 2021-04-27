package com.example.alias.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alias.R
import kotlinx.android.synthetic.main.activity_rules.*

class RulesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)

        rulesTextView.text = resources.assets.open("rules.txt")
            .readBytes()
            .toString(Charsets.UTF_8)
    }
}