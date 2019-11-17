package com.example.myapplication.utils

import java.io.FileOutputStream
import java.lang.StringBuilder
import java.util.*

class Lol {

}

data class User(var firstname: String = "", var lastname: String = "")

fun Lol.yes(): Boolean {
    return true
}


fun test()
{

    val instance = Lol()
    instance.yes()

    Lol().let {
        // Scoped variable
        it.yes()
    }

    val instance2: Lol? = null

    instance2?.let {
        // Only when not null
    }

    // Call yes after construct before return
    val instance3: Lol = Lol().apply { yes() }

    // Automatisation of factory
    val sentence = StringBuilder().run({
        append("hello")
        append("kotlin")
        toString()
    })

    // With do not require recall instance when not fluent setter
    val user = User()
    with(user) {
        this.firstname = "john"
        this.lastname = "gates"
    }

    // Kotlin

    var properties = Properties()
    with(properties) {
        setProperty("name", "Bob")
        setProperty("age", "10")
    }

    FileOutputStream("config.properties").use { fileOutputStream -> {
        properties.store(fileOutputStream, null)
    } }
}