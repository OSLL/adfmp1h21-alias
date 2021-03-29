package com.example.alias.storage

class GameSettings {
    companion object {
        private const val ONE_SECOND = 1_000L

        const val roundTime: Long = 3L * ONE_SECOND
        const val categoryResources: String = "categories"

        var wrongPenalty = false
    }
}