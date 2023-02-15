package com.example.predictionapp

import androidx.lifecycle.ViewModel

class Quiz_view_model : ViewModel() {
    private val questionBank = listOf(
        Quiz(R.string.question_sky, true),
        Quiz(R.string.question_water, true),
        Quiz(R.string.question_human, true),
        Quiz(R.string.question_oswindows, false),
        Quiz(R.string.question_marce, false),
        Quiz(R.string.question_google, false),
    )
    private var currentindex = 0;
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentindex].answer
    val currentQuestionText: Int
        get() = questionBank[currentindex].textResId

    fun moveToNext() {
        currentindex = (currentindex + 1) % questionBank.size
    }

    fun moveToPrev() {
        currentindex = (currentindex - 1) % questionBank.size
    }
}