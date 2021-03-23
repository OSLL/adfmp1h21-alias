package com.example.alias.storage

class GameSettings {
    companion object {
        private const val ONE_SECOND = 1_000L

        const val roundTime: Long = 10L * ONE_SECOND
        const val categoryResources: String = "category"

        var wrongPenalty = false
    }
}