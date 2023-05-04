package ru.verb.hometask_1

sealed class Mood(val messageToTheWorld: String)

object Angry: Mood("I'm so angry!")
object Happy: Mood("I'm so happy!")
object Sad: Mood("I'm so sad!")
data class MyCustomMood(val myOwnMood: String): Mood(myOwnMood)