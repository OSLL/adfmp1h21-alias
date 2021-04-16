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

        val categoryAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            GameState.categories.keys.toList()
        )

        categoryListView.apply {
            adapter = categoryAdapter
        }

        categoryListView.setOnItemClickListener { _, view, _, _ ->
            val category = (view as TextView).text.toString()
            choiceCategory(category)

            val gamePreviewActivityIntent = Intent(this, GamePreviewActivity::class.java)
            startActivity(gamePreviewActivityIntent)
        }
    }

    fun choiceCategory(categoryName: String) {
        GameState.categories[categoryName]?.let {
            val words = readAssetResource(it)
            GameState.categoryWords.addAll(words)
        }
    }

    fun readAssetResource(categoryPath: String): List<String> {
        val categoryFile = File(GameSettings.categoryResources).resolve(categoryPath)
        return resources.assets.open(categoryFile.toString())
            .readBytes()
            .toString(Charsets.UTF_8)
            .split("\n")
    }
}