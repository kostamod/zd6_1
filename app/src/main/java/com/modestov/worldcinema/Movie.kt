package com.modestov.worldcinema

data class Movie(val id: String, val title: String, val year: String, val description: String) {
    val poster: String get() = "file:///android_asset/posters/$id.jpg"
}

val movies = listOf(
    Movie("witcher", "The Witcher", "2019", "Геральт путешествует по Континенту и охотится на чудовищ."),
    Movie("lucifer", "Lucifer", "2016", "Люцифер переезжает в Лос-Анджелес и помогает раскрывать преступления."),
    Movie("lost", "LOST", "2004", "Выжившие после авиакатастрофы пытаются разгадать тайны острова."),
    Movie("magicians", "The Magicians", "2015", "Студенты школы магии открывают для себя загадочный мир.")
)
