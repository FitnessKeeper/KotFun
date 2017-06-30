package com.fitnesskeeper.kotfun

import java.math.BigDecimal
import kotlin.Double.Companion.MAX_VALUE

// Variables
val someFixedInt = 10
var someVariableInt = 20
val v : Double = 30.0

// Type coercion
val foo = "Runkeeper is the #" + 1 + " app!"

// Destructuring a data class. Super nifty
fun someFunc() {
    val (amount, currency) = Money(100.0, "$")

    // Can also ignore some values
    val (_, currency2) = Money(100.0, "EUR")
}

// Regular 'ole function
fun Money.convertToDollars(money: Money): Money {
    when (money.currency) {
        "$" -> return money
        "EUR" -> return Money(money.amount * 1.10, "$")
        else -> throw IllegalArgumentException("Currency not handled.")
    }
}

// Same as above, but done as a single expression function
fun Money.convertToDollarsSingleExpr(money: Money): Money = when (money.currency) {
    "$" -> money
    "EUR" -> Money(money.amount * 1.10, "$")
    else -> throw IllegalArgumentException("Currency not handled.")
}

// Same as above, but done as a property
val Money.dol : Money
    get() = convertToDollarsSingleExpr(this)

// This is how you can use that handy property.
val eurosToDollars = Money(100.0, "EUR").dol

// Demonstrates either using named params or not. You can't mix named / positional parameters
fun Money.add(money: Money) = Money(currency = "$", amount = (amount + convertToDollars(money).amount))
//fun Money.add(money: Money) = Money((amount + convertToDollars(money).amount), "$")

// Now we can add money easily!
operator fun Money.plus(money: Money) = add(money)

// E.g.,
val allTheMoney = eurosToDollars + Money(MAX_VALUE, "$")

// Let's make that even easier
operator fun Money.plus(sameCurrencyAmount: Double) = Money(amount + sameCurrencyAmount, currency)
val allTheMoney2 = eurosToDollars + MAX_VALUE

// Can make an infix operator to make domain specific languages (DSLs) more easily
infix fun Double.percentOf(money: Money) = Money(money.amount * (this/100), money.currency)
val tenPercentOfAllTheMoney = 10.0 percentOf allTheMoney

infix fun Double.percentOf(other: Double) = other * (this/100)
val p = 25.0 percentOf 100.0

// Can inject a property to make type conversions easier
val Double.bd get() = BigDecimal(this)
val bd = 25.0.bd // Type is BigDecimal

// This looks a bit like this
val floatVal = 10f

val waldo = "It's where stupid"
fun whereIsWhen(): String =
    when (waldo) {
        "A" -> "Nope"
        "B" -> "Not that either"
        else -> "I give up"
    }

// THIS IS SO COOL. Now you can write stubs that compile more easily. I don't know why that's so exciting, but... :-P
fun coolFunction(): Nothing {
    TODO("Write an awesome function here!!!")
}

// Functions as parameters! Java-less madness!
data class User(val name: String, val email: String)
fun findEmails(users: List<User>, predicate: (String) -> (Boolean)): List<User> = emptyList()
val users = emptyList<User>()
val matchingEmails = findEmails(users, { name -> name.endsWith("Stucklen")})
val matchingEmails2 = findEmails(users, { it.endsWith("Stucklen")})
fun coolPred(name: String): Boolean = name.endsWith("Stucklen")
val matchingEmails3 = findEmails(users, ::coolPred)

// We can make it look more like a DSL by omitting the parens too (for a single function call lambda)
val matchingEmails4 = findEmails(users) {
    it.endsWith("Stucklen")
}

// Kotlin has much of this built in already though and it's pretty cool:
val matchingEmails5 = users.filter { it.email.endsWith("Stucklen") }
        .sortedBy { it.name }
        .map { it.name to it.email }

// Nullability
val notNull : Double = 10.0
val nullable : Double? = null
val alsoNotNull = if (notNull != null) notNull else -1
val safeCall = nullable?.div(10.0)
val alsoAlsoNotNull = notNull ?: -1
//val crashyMcCrashFace = nullable!!.toString()