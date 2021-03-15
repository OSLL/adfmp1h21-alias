package com.example.alias.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import com.example.alias.R
import com.example.alias.storage.GameState
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {
    // TODO: add categories
    private val categories = mapOf(
        "key" to listOf("value"),
        "Школа" to listOf("value"),
        "Программирование" to listOf("value"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val categoryAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            categories.keys.toList()
        )

        categoryListView.apply {
            adapter = categoryAdapter
        }

        categoryListView.setOnItemClickListener { parent, view, position, id ->
            val category = (view as TextView).text
            categories[category]?.let { GameState.categories.addAll(it) }

            val gamePreviewActivityIntent = Intent(this, GamePreviewActivity::class.java)
            startActivity(gamePreviewActivityIntent)
        }
    }
}