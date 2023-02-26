package com.example.predictionapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

private const val EXTRA_ANSWER = "com.example.predictionapp.answer_is_true"
const val EXTRA_ANSWER_SHOWN = "com.example.predictionapp.answer_is_false"

class CheatActivity : AppCompatActivity() {
    private var answer = false
    private lateinit var textViewCheat: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        answer = intent?.getBooleanExtra(EXTRA_ANSWER, false) ?: false
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
    private fun setAnswerShownResult(isAnswerShown: Boolean){
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown)
        }
        setResult(Activity.RESULT_OK,data)
    }
    private fun showAnswerDialog() {
        AlertDialog.Builder(this)
            .setTitle("Choose tablet")
            .setMessage("Are you sure?")
            .setPositiveButton("Yes") { _, _ ->
                textViewCheat.visibility = View.VISIBLE
                textViewCheat.text = if (answer) "True" else "False"
                setAnswerShownResult(true)
            }
            .setNegativeButton("No", null)
            .setCancelable(true)
            .create()
            .show()
    }
    companion object{
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent{
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER, answerIsTrue)
            }
        }
    }

}
