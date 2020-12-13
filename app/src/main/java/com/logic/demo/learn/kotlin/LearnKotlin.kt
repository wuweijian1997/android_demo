package com.logic.demo.learn.kotlin

fun main() {
    val str: String = "Hello World"
    println("Hello Kotlin")
}

/*fun max(num1: Int, num2: Int): Int {
    return if (num1 > num2) num1
    else num2
}*/

fun max(num1: Int, num2: Int): Int {
    var value = 0
    if(num1 > num2) {
        value = num1
    } else {
        value = num2
    }
    return value
}

fun getScore(name: String) = when {
    name.startsWith("Tom") -> 86
    name == "Jim" -> 77
    name == "Jack" -> 95
    else -> 0
}

interface Study {
    fun readBooks()

    fun doHomework()
}

class Student : Study {
    override fun readBooks() {
        TODO("Not yet implemented")
    }

    override fun doHomework() {
        TODO("Not yet implemented")
    }
}