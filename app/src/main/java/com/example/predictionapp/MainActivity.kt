package com.example.predictionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var button_no: Button
    private lateinit var button_yes: Button
    private lateinit var nextQuestion: ImageView
    private lateinit var prevQuestion: ImageView
    private lateinit var textview_main: TextView

    private val quizViewModel: Quiz_view_model by lazy {
        val provider = ViewModelProvider(this)
        provider.get(Quiz_view_model::class.java)
    }
    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        textview_main.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when {
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        Toast.makeText(this@MainActivity, messageResId, Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_no = findViewById(R.id.button_no)
        button_yes = findViewById(R.id.button_yes)
        nextQuestion = findViewById(R.id.imageView_next)
        prevQuestion = findViewById(R.id.imageView_prev)
        textview_main = findViewById(R.id.textView_main)

        button_no.setOnClickListener{
            checkAnswer(true)
        }
        button_yes.setOnClickListener{
            checkAnswer(false)
        }
        nextQuestion.setOnClickListener{
            quizViewModel.moveToNext()
            updateQuestion()
        }
        prevQuestion.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion()
        }
        updateQuestion()
    }
}