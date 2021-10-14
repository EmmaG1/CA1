package org.wit.ca1.console.main

import mu.KotlinLogging
import org.wit.ca1.console.models.RecipeModel


private val logger = KotlinLogging.logger {}

var recipe = RecipeModel()
val recipes = ArrayList<RecipeModel>()

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

    if (recipe.title.isNotEmpty() && recipe.ingred.isNotEmpty()) {
        recipes.add(recipe.copy())
        logger.info("Recipe Added : [ $recipe ]")
    }
    else
        logger.info("Recipe Not Added")
    recipe.id++
}

fun updateRecipe() {
    println("Update Recipe")
    println()
    print("Enter a new Title for [ " + recipe.title + " ] : ")
    recipe.title = readLine()!!
    print("Enter updated ingredients for [ " + recipe.ingred + " ] : ")
    recipe.ingred = readLine()!!
    println("You updated [ " + recipe.title + " ] for title " +
            "and [ " + recipe.ingred + " ] for ingredients")
}

fun listRecipes() {
    println("List All Recipes")
    println()
    recipes.forEach { logger.info("${it}") }
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : RecipeModel? {
    var foundPlacemark: RecipeModel? = recipes.find { p -> p.id == id }
    return foundPlacemark
}