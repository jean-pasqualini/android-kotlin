package com.example.myapplication.activity.country

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.CountryAdapter

import kotlinx.android.synthetic.main.country_list.*

class CountryListActivity : AppCompatActivity(), View.OnClickListener {

    var countries = arrayOf<String>(
        "France",
        "Belgique",
        "Guatemala",
        "Italie",
        "Russie",
        "Maroc",
        "Algérie",
        "Japon",
        "Chine",
        "Danemark",
        "Espagne",
        "Portugal"
    )

    var adapter = CountryAdapter(countries, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.country_list)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = this.adapter

        update_countries.setOnClickListener { this.updateCountries() }
    }

    override fun onClick(view: View) {
        if (view.tag != null) {
            var index = view.tag as Int
            var country = this.countries[index]

            Toast.makeText(this, "Pays sélection : ${country}", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateCountries() {
        var lastLetter = countries[0].last()

        for ((index, country) in countries.withIndex()) {
            if (lastLetter.isUpperCase()) {
                countries[index] = country.toLowerCase().capitalize()
            } else {
                countries[index] = country.toUpperCase()
            }
        }

        this.adapter.notifyDataSetChanged()
    }

}
