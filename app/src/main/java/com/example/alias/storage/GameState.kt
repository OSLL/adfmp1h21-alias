package com.example.alias.storage

import java.util.*

class GameState {
    companion object Instance {
        // FIXME: hardcore data
        val teams: MutableList<String> = mutableListOf(
            "Team1",
            "Team2"
        )

        val categories: Map<String, String> = mapOf(
            "Школа" to "school.txt",
            "Кулинария" to "cooking.txt"
        )

        val categoryWords: MutableList<String> = mutableListOf()
        val teamRating: SortedMap<String, Int> = sortedMapOf<String, Int>()

        var teamCounter = 0
        var roundCounter = 1
    }
}