package com.embibe.iibnanded.model

data class QuestionModel(var question: String = "",
                         var optionA: String = "",
                         var optionB: String = "",
                         var optionC: String = "",
                         var optionD: String = "",
                         var answer: String = "") {
    var id = 0
}