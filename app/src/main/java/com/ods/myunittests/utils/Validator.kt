package com.ods.myunittests.utils

object Validator {

    fun validateInput(amount: Int, description: String): Boolean {
        return !(amount <= 0 || description.isEmpty())
    }
}