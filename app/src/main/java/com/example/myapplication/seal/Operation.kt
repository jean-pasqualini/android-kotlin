package com.example.myapplication.seal

sealed class Operation {
    class Add(val value: Int) : Operation()
    class Substract(val value: Int) : Operation()
    object Increment : Operation()
    object Decrement : Operation()
}

fun execute(x: Int, operation: Operation) = when(operation) {
    is Operation.Add -> x + operation.value
    is Operation.Substract -> x - operation.value
    Operation.Increment -> x + 1
    Operation.Decrement -> x - 1
}

private fun test() {
    var age = execute(10, Operation.Add(1))
    println("Addition: age $age")
}