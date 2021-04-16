package com.example.alias.activities

import com.example.alias.storage.GameState
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TeamActivityTest {

    @Test
    fun `test validate team name`() {
        val teamActivity = TeamActivity()
        assertTrue(teamActivity.validation("newTeam"))
    }

    @Test
    fun `test validate existed team name`() {
        GameState.teamRating["newTeam"] = 0

        val teamActivity = TeamActivity()
        assertFalse(teamActivity.validation("newTeam"))
    }
}