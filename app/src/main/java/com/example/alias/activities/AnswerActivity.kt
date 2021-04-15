package com.example.alias.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alias.R
import com.example.alias.storage.GameState
import com.example.alias.utils.AnswerWord
import com.example.alias.utils.SwitchRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_answer.*

class AnswerActivity : AppCompatActivity(), SwitchRecyclerViewAdapter.OnItemClickListener {
    private lateinit var answerWords: List<AnswerWord>
    private lateinit var switchRecyclerViewAdapter: SwitchRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        answerWords = intent.getParcelableArrayListExtra("data")
        switchRecyclerViewAdapter = SwitchRecyclerViewAdapter(
            answerWords,
            this
        )

        answerRecyclerListView.apply {
            adapter = switchRecyclerViewAdapter
            layoutManager = LinearLayoutManager(this@AnswerActivity)
            setHasFixedSize(true)
        }

        nextButton.setOnClickListener {
            val teams = GameState.teamRating.keys.toList()
            val currentTeam = GameState.teamCounter % GameState.teamRating.size
            val team = teams[currentTeam]
            val points = answerWords.map { it.isGuessed }.filter { it }.count()

            GameState.teamRating[team] = GameState.teamRating.getOrDefault(team, 0) + points
            GameState.teamCounter++
            GameState.roundCounter++

            val gamePreviewActivityIntent = Intent(
                this,
                GamePreviewActivity::class.java
            )
            startActivity(gamePreviewActivityIntent)
        }
    }

    override fun onItemClick(position: Int) {
        val clickedItem = answerWords[position]
        clickedItem.isGuessed = !clickedItem.isGuessed

        switchRecyclerViewAdapter.notifyItemChanged(position)
    }
}