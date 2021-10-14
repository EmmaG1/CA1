package org.wit.ca1.console.main

import mu.KotlinLogging
import org.wit.ca1.console.models.RecipeModel

private val logger = KotlinLogging.logger {}

var recipe = RecipeModel()

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
    recipe.title = readLine()!!
    print("Enter Ingredients : ")
    recipe.ingred = readLine()!!
    println("You entered [ " + recipe.title + " ] for title " +
            "and [ " + recipe.ingred + " ] for description")
}

fun updateRecipe() {
    println("Update Recipe")
    println()
    print("Enter a new Title for [ " + recipe.title + " ] : ")
    recipe.title = readLine()!!
    print("Enter updated ingredients for [ " + recipe.ingred + " ] : ")
    recipe.ingred = readLine()!!
    println("You updated [ " + recipe.title + " ] for title " +
            "and [ " + recipe.ingred + " ] for description")
}

fun listRecipes() {
    println("You Chose List All Recipes")
}