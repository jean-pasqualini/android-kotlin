package com.example.myapplication.enum

enum class Direction(val description: String) {
    NORTH("Nord") {
        override fun action() = "Marcher"
    },
    EAST("Est") {
        override fun action() = "Courir"
    },
    SOUTH("Sud") {
        override fun action() = "Saut"
    },
    WEST("Ouest") {
        override fun action() = "Se resposer"
    };

    abstract fun action(): String
}