package edu.uw.basickotlin

import java.lang.IllegalArgumentException
import kotlin.math.roundToInt

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(a: Any): String {
    if (a is String) {
        if (a == "Hello") {
            return "world"
        } else {
            return "I don't understand"
        }
    } else if (a is Int) {
        if (a == 0) {
            return "zero"
        } else if (a == 1) {
            return "one"
        } else if (a >= 2 && a < 10) {
            return "low number"
        } else {
            return "a number"
        }
    } else {
        return "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(a: Int, b: Int): Int {
    return a + b
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(a: Int, b: Int): Int {
    return a - b;
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int),
// returns an Int, and applies the passed-in-function to the arguments
fun mathOp(a: Int, b: Int, body: (Int, Int) -> Int): Int {
    return body(a, b)
}

// write a class "Person" with first name, last name and age
class Person(var firstName: String, var lastName: String, var age: Int) {
    val debugString: String = "Person $firstName $lastName $age"
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money(val amount: Int, val currency: String) {
    init {
        val currencies = arrayOf("USD", "GBP", "EUR", "CAN")
        if (this.amount < 0) throw IllegalArgumentException()
        if (this.currency !in currencies) throw IllegalArgumentException()
    }
    fun convert(toCurrency: String): Money {
        if (this.currency == "USD") {
            when (toCurrency) {
                "GBP" -> return Money(this.amount / 2, toCurrency)
                "EUR" -> return Money((this.amount * 1.5).roundToInt(), toCurrency)
                "CAN" -> return Money((this.amount * 1.25).roundToInt(), toCurrency)
            }
        } else if (this.currency == "GBP") {
            when (toCurrency) {
                "USD" -> return Money(this.amount * 2, toCurrency)
                "EUR" -> return Money(this.amount * 3, toCurrency)
                "CAN" -> return Money((this.amount * 2.5).roundToInt(), toCurrency)
            }
        } else if (this.currency == "EUR") {
            when (toCurrency) {
                "USD" -> return Money((this.amount / 1.5).roundToInt(), toCurrency)
                "GBP" -> return Money(this.amount / 3, toCurrency)
                "CAN" -> return Money((this.amount / 1.2).roundToInt(), toCurrency)
            }
        } else if (this.currency == "CAN") {
            when (toCurrency) {
                "USD" -> return Money((this.amount / 1.25).roundToInt(), toCurrency)
                "GBP" -> return Money((this.amount / 2.5).roundToInt(), toCurrency)
                "EUR" -> return Money((this.amount * 1.2).roundToInt(), toCurrency)
            }
        }
        return Money(this.amount, toCurrency)
    }

    operator fun plus(other: Money): Money {
        if (this.currency != other.currency) {
            return Money(this.amount + convert(other.currency).amount, this.currency)
        }
        return Money(this.amount + other.amount, this.currency)
    }
}