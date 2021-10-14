package org.wit.ca1.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var title = ""
var ingred = ""

fun main(args: Array<String>) {
    logger.info { "Launching Recipe Console App" }
    println("Recipe Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addRecipe()
            2 -> updateRecipe()
            3 -> listRecipes()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Recipe Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String?

    println("MAIN MENU")
    println(" 1. Add Recipe")
    println(" 2. Update Recipe")
    println(" 3. List All Recipes")
    println("-1. Exit")
    println()
    print("Enter Option : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addRecipe(){
    println("Add Recipe")
    println()
    print("Enter a Title : ")
    title = readLine()!!
    print("Enter Ingredients : ")
    ingred = readLine()!!
    println("You entered $title for title and $ingred for ingredients.")
}

fun updateRecipe() {
    println("Update Recipe")
    println()
    print("Enter a new Title for [ $title ] : ")
    title = readLine()!!
    print("Enter a new Description for [ $ingred ] : ")
    ingred = readLine()!!
    println("You updated [ $title ] for title and [ $ingred ] for description")
}

fun listRecipes() {
    println("You Chose List All Recipes")
}