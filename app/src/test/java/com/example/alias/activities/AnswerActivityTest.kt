package com.example.alias.activities

import com.example.alias.storage.GameState
import com.example.alias.utils.AnswerWord
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test

class AnswerActivityTest {

    @After
    fun cleanUp() {
        GameState.teamCounter = 0
        GameState.roundCounter = 0
    }

    @Test
    fun `test answers with data`() {
        // Arrange
        val answerWords: List<AnswerWord> = listOf(
            AnswerWord("1", true),
            AnswerWord("2", true),
            AnswerWord("3", false),
            AnswerWord("4", true),
            AnswerWord("5", false),
            AnswerWord("6", false),
            AnswerWord("7", true),
            AnswerWord("8", true),
            AnswerWord("9", false),
        )

        GameState.teamRating["Team1"] = 0
        assertEquals(0, GameState.teamRating["Team1"])
        assertEquals(0, GameState.teamCounter)
        assertEquals(0, GameState.roundCounter)

        // Act
        val answerActivity = AnswerActivity()
        answerActivity.calculateResult(answerWords, false)

        // Assert
        assertEquals(answerWords.filter { it.isGuessed }.count(), GameState.teamRating["Team1"])
    }

    @Test
    fun `test answers with empty data`() {
        // Arrange
        val answerWords: List<AnswerWord> = listOf()

        GameState.teamRating["Team1"] = 0
        assertEquals(0, GameState.teamRating["Team1"])
        assertEquals(0, GameState.teamCounter)
        assertEquals(0, GameState.roundCounter)

        // Act
        val answerActivity = AnswerActivity()
        answerActivity.calculateResult(answerWords, false)

        // Assert
        assertEquals(answerWords.filter { it.isGuessed }.count(), GameState.teamRating["Team1"])
    }
}