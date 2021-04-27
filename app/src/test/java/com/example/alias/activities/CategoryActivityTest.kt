package com.example.alias.activities

import com.example.alias.storage.GameState
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CategoryActivityTest {

    @Test
    fun `test choice category with invalid name`() {
        val categoryName = "InvalidCategory"
        assertFalse(GameState.categories.containsKey(categoryName))
        assertTrue(GameState.categoryWords.isEmpty())

        val categoryActivity = CategoryActivity()
        categoryActivity.choiceCategory(categoryName)
        assertTrue(GameState.categoryWords.isEmpty())
    }
}