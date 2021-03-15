package com.example.alias.storage

class GameState {
    companion object Instance {
        val teams = mutableListOf<String>()
        val categories = mutableListOf<String>()
        val teamRating = mutableMapOf<String, Int>()

        var teamCounter = 0
        var roundCounter = 1
    }
}