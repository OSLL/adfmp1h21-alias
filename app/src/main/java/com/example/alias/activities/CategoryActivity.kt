package com.example.alias.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.alias.R
import com.example.alias.storage.GameSettings
import com.example.alias.storage.GameState
import kotlinx.android.synthetic.main.activity_category.*
import java.io.File

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val categoryAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            GameState.categories.keys.toList()
        )

        categoryListView.apply {
            adapter = categoryAdapter
        }

        categoryListView.setOnItemClickListener { _, view, _, _ ->
            val category = (view as TextView).text

            GameState.categories[category]?.let {
                val categoryPath = File(GameSettings.categoryResources).resolve(it)
                val words = resources.assets.open(categoryPath.toString())
                    .readBytes()
                    .toString(Charsets.UTF_8)
                    .split("\n")

                GameState.categoryWords.addAll(words)
            }

            val gamePreviewActivityIntent = Intent(this, GamePreviewActivity::class.java)
            startActivity(gamePreviewActivityIntent)
        }
    }
}