package com.example.predictionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

private const val EXTRA_ANSWER = "com.example.predictionapp.answer_is_true"
private const val EXTRA_ANSWER_SHOWN = "com.example.predictionapp.answer_is_false"

class CheatActivity : AppCompatActivity() {
    private var answer = false
    private lateinit var textViewCheat: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        answer = intent?.getBooleanExtra("answer", false) ?: false
        setContentView(R.layout.activity_cheatactivity)
        textViewCheat = findViewById(R.id.textView_Cheat)
        textViewCheat.visibility = View.GONE
        findViewById<Button>(R.id.button_TextCheat).setOnClickListener {
            showAnswerDialog()
        }
        if (savedInstanceState != null) {
            val answerShown = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN, false)
            if (answerShown)
                textViewCheat.visibility = View.VISIBLE
            answer = savedInstanceState.getBoolean(EXTRA_ANSWER, answer)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(EXTRA_ANSWER_SHOWN, textViewCheat.visibility == View.VISIBLE)
        outState.putBoolean(EXTRA_ANSWER, answer)
    }

    private fun showAnswerDialog() {
        AlertDialog.Builder(this)
            .setTitle("Choose tablet")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes") { _, _ ->
                textViewCheat.visibility = View.VISIBLE
                textViewCheat.text = if (answer) "True" else "False"
            }
            .setNegativeButton("No", null)
            .setCancelable(true)
            .create()
            .show()
    }

}