package com.example.myapplication.domain

import javax.persistence.Entity

@Entity
class User {

    var id: Long = 0

    var name: String? = null
    var age: Int? = 0
    var email: String? = null
}