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

class Money(val value: Int) {
    operator fun plus(money: Money): Money {
        val sum = value + money.value
        return Money(sum)
    }

    operator fun plus(newValue: Int): Money {
        val sum = value + newValue
        return Money(sum)
    }
}

fun example(func: (String, Int) -> Unit) {
    func("Hello", 123)
}

fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}
val result1 = num1AndNum2(100, 200) {n1, n2 -> n1 + n2}