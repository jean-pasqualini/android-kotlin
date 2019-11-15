package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.myapplication.common.*;
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.title as show_button
import kotlinx.android.synthetic.main.activity_main.toolbar as toolbar

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

        show_button.setOnClickListener {
            var fragment = ConfirmDeleteDialogFragment()

            fragment.listener = object: ConfirmDeleteDialogFragment.ConfirmDeleteListener {
                override fun onDialogPositiveClick() {
                    Log.i("Main activity", "oui")

                    var fileListFragment = FileListDialogFragment()

                    fileListFragment.show(supportFragmentManager, "fileList")
                }

                override fun onDialogNegativeClick() {
                    Log.i("Main activity", "non")
                }

            }

            fragment.show(supportFragmentManager, "Confirm Delete")
        }

        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = MenuInflater(this)
        menuInflater.inflate(R.menu.action_bar, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_lol -> {
                Toast.makeText(this, "Vous êtes en mode lol", Toast.LENGTH_SHORT).show()

                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
