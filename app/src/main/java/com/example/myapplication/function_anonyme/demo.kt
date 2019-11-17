package com.example.myapplication.function_anonyme

// HighOrderFunction

fun foo (paramFunction: (Int) -> Boolean) {
    println("resultat : ${paramFunction(3)}")
}

fun bar(value: Int) : Boolean {
    return value > 2
}

fun test() {
    foo(::bar) // Similaire à un callback non anonyme
    foo { value -> value > 5 }
    foo { it > 5 }
    foo() { it > 5 }
}