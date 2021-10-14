package org.wit.ca1.console.main

import mu.KotlinLogging
import org.wit.ca1.console.models.RecipeModel


private val logger = KotlinLogging.logger {}

//var recipe = RecipeModel()
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
            4 -> searchRecipe()
            -99 -> dummyData()
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
    println(" 4. Search Recipes")
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
    var aRecipe = RecipeModel()
    println("Add Recipe")
    println()
    print("Enter a Title : ")
    aRecipe.title = readLine()!!
    print("Enter Ingredients : ")
    aRecipe.ingred = readLine()!!

    if (aRecipe.title.isNotEmpty() && aRecipe.ingred.isNotEmpty()) {
        aRecipe.id = recipes.size.toLong()
        recipes.add(aRecipe.copy())
        logger.info("Recipe Added : [ $aRecipe ]")
    }
    else
        logger.info("Recipe Not Added")
}

fun updateRecipe() {
    println("Update Recipe")
    println()
    listRecipes()
    var searchId = getId()
    val aRecipe = search(searchId)

    if(aRecipe != null) {
        print("Enter a new Title for [ " + aRecipe.title + " ] : ")
        aRecipe.title = readLine()!!
        print("Enter a new Description for [ " + aRecipe.ingred + " ] : ")
        aRecipe.ingred = readLine()!!
        println(
            "You updated [ " + aRecipe.title + " ] for title " +
                    "and [ " + aRecipe.ingred + " ] for description"
        )
    }
    else
        println("Recipe Not Updated...")

}

fun listRecipes() {
    println("List All Recipes")
    println()
    recipes.forEach { logger.info("${it}") }
    println()
}

fun searchRecipe() {

    var searchId = getId()
    val aRecipe = search(searchId)

    if(aRecipe != null)
        println("Recipe Details [ $aRecipe ]")
    else
        println("Recipe Not Found...")
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
    var foundRecipe: RecipeModel? = recipes.find { p -> p.id == id }
    return foundRecipe
}

fun dummyData() {
    recipes.add(RecipeModel(1, "New York New York", "So Good They Named It Twice"))
    recipes.add(RecipeModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    recipes.add(RecipeModel(3, "Waterford City", "You get great Blaas Here!!"))
}