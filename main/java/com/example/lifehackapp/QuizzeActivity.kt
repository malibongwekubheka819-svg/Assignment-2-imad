package com.example.lifehackapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizzeActivity : AppCompatActivity() {
    private var index = 0
    private var score = 0
    private lateinit var questionText: TextView
    private lateinit var feedbackText: TextView


    private val questions = arrayOf(
        "Does putting a wet phone in front of a hair dryer fix it",
        "Using two monitors does it make your work faster",
        "Does drinking soda and tea count towards your daily water intake",
        "Does note-taking help you remember tasks down improve memory",
        "Will overnight charging harm a laptop battery over time",
    )

    private val answers = arrayOf(false, true, false, true, false)

    private val explanations = arrayOf(
        "Myth: No, it usually makes thing worse.",
        "Hack: Yes, for most people it does.",
        "Myth: While caffeine has a slight dehydrating effect, the water in these drinks still hydrates you.",
        "Hack: Note-taking does help you remember tasks better .",
        "Myth: No, overnight charging usually won't harm a modern laptop battery immediately.",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quizze)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        questionText = findViewById(R.id.questionTextView)
        feedbackText = findViewById(R.id.feedbackTextView)

        val trueButton = findViewById<Button>(R.id.trueButton)
        val falseButton = findViewById<Button>(R.id.falseButton)

        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }

        updateQuestion()
    }

    private fun updateQuestion() {
        if (index < questions.size) {
            questionText.text = questions[index]
            feedbackText.text = ""
            findViewById<Button>(R.id.trueButton).isEnabled = true
            findViewById<Button>(R.id.falseButton).isEnabled = true
        } else {
            showFinalScore()

        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        if (index < questions.size) {
            // Disable buttons to prevent multiple clicks for the same question
            findViewById<Button>(R.id.trueButton).isEnabled = false
            findViewById<Button>(R.id.falseButton).isEnabled = false

            if (userAnswer == answers[index]) {
                score++
                Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, R.string.wrong, Toast.LENGTH_SHORT).show()
            }
            feedbackText.text = explanations[index]
            index++
            
            // Delay moving to the next question to show feedback
            questionText.postDelayed({
                updateQuestion()
            }, 2000)

        }
    }

    private fun showFinalScore() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("score", score)
        intent.putExtra("total", questions.size)
        startActivity(intent)
        finish()
    }
}
