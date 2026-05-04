package com.example.lifehackapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val layout = findViewById<LinearLayout>(R.id.reviewLayout)

        val questions = arrayOf(
            "Does putting a wet phone in front of a hair dryer fix it",
            "Using two monitors does it make your work faster",
            "Does drinking soda and tea count towards your daily water intake",
            "Does note-taking help you remember tasks down improve memory",
            "Will overnight charging harm a laptop battery over time"
        )

        val answers = arrayOf(false, true, false, true, false)

        for (i in questions.indices) {
            val tv = TextView(this)
            val answerText = if (answers[i]) "Hack" else "Myth"
            tv.text = "${questions[i]}\nAnswer: $answerText\n"
            tv.setPadding(0, 0, 0, 20)
            layout.addView(tv)
        }
    }
}
