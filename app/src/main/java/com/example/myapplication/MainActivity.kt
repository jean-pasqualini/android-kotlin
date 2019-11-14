package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.common.*;
import kotlinx.android.synthetic.main.activity_main.*

class Car(val wheelsCount: Int = 4) {
    public fun honk() {
        println("Pouet!")
    }

    fun hontForWheels() {
        for (i in 1..this.wheelsCount) {
            println("raah")
            println("tatouille")
        }
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(name)
        describePerson()

        var car = Car(4)

        println("La voiture à ${car.wheelsCount} roue")

        car.hontForWheels()

        call.setOnClickListener {
            val intent = Intent(this, Details::class.java)
            this.startActivity(intent)
        }
    }
}
