package com.example.alias.storage

import java.util.*

class GameState {
    companion object Instance {
        val categories: Map<String, String> = mapOf(
            "Школа" to "school.txt",
            "Кулинария" to "cooking.txt"
        )

        val categoryWords: MutableList<String> = mutableListOf()
        val teamRating: SortedMap<String, Int> = sortedMapOf()

        var teamCounter = 0
        var roundCounter = 0
    }
}