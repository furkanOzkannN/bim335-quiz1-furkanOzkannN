package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var editText1: EditText? = null
    private var editText2: EditText? = null
    private var startGameButton: Button? = null
    private var button1: Button? = null
    private var button2: Button? = null
    private var scoreTextView: TextView? = null

    private var score1 = 0
    private var score2 = 0
    private var player1Name = ""
    private var player2Name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View'ları bağla
        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        startGameButton = findViewById(R.id.startGameButton)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        scoreTextView = findViewById(R.id.scoreTextView)

        resetGame()

        startGameButton?.setOnClickListener {
            startGame()
        }

        button1?.setOnClickListener {
            playerScored(1)
        }

        button2?.setOnClickListener {
            playerScored(2)
        }
    }

    private fun startGame() {
        player1Name = editText1?.text.toString().trim()
        player2Name = editText2?.text.toString().trim()

        if (player1Name.isEmpty()) {
            player1Name = getString(R.string.player1_hint)
        }
        if (player2Name.isEmpty()) {
            player2Name = getString(R.string.player2_hint)
        }

        score1 = 0
        score2 = 0
        updateScore()

        editText1?.isEnabled = false
        editText2?.isEnabled = false

        button1?.text = "$player1Name kazandı"
        button2?.text = "$player2Name kazandı"

        button1?.isEnabled = true
        button2?.isEnabled = true

        scoreTextView?.visibility = View.VISIBLE
    }

    private fun playerScored(player: Int) {
        if (player == 1) {
            score1++
        } else {
            score2++
        }

        updateScore()

        if (score1 == 5) {
            endGame(player1Name)
        } else if (score2 == 5) {
            endGame(player2Name)
        }
    }

    private fun updateScore() {
        scoreTextView?.text = getString(R.string.score_format, score1, score2)
    }

    private fun endGame(winnerName: String) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("WINNER_NAME", winnerName)
        startActivity(intent)
    }

    private fun resetGame() {
        score1 = 0
        score2 = 0
        player1Name = ""
        player2Name = ""

        editText1?.setText("")
        editText2?.setText("")
        editText1?.isEnabled = true
        editText2?.isEnabled = true

        button1?.isEnabled = false
        button2?.isEnabled = false
        button1?.text = getString(R.string.player1_hint)
        button2?.text = getString(R.string.player2_hint)

        scoreTextView?.visibility = View.GONE
        scoreTextView?.text = "0 - 0"
    }

    override fun onResume() {
        super.onResume()
        resetGame()
    }
}