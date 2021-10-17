package org.wit.ca1.console.main

import mu.KotlinLogging
import org.wit.ca1.console.models.RecipeModel
import org.wit.ca1.console.models.RecipeMemStore
import org.wit.ca1.console.views.RecipeView

private val logger = KotlinLogging.logger {}

//var recipe = RecipeModel()
//val recipes = ArrayList<RecipeModel>()
val recipes = RecipeMemStore()
val recipeView = RecipeView()

fun main(args: Array<String>) {
    logger.info { "Launching Recipe Console App" }
    println("Recipe Kotlin App Version 1.0")

    var input: Int

    do {
        input = recipeView.menu()
        when(input) {
            1 -> addRecipe()
            2 -> updateRecipe()
            3 -> recipeView.listRecipes(recipes)
            4 -> searchRecipe()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Recipe Console App" }
}

//fun menu() : Int {
//
//    var option : Int
//    var input: String?
//
//    println("MAIN MENU")
//    println(" 1. Add Recipe")
//    println(" 2. Update Recipe")
//    println(" 3. List All Recipes")
//    println(" 4. Search Recipes")
//    println("-1. Exit")
//    println()
//    print("Enter Option : ")
//    input = readLine()!!
//    option = if (input.toIntOrNull() != null && !input.isEmpty())
//        input.toInt()
//    else
//        -9
//    return option
//}

fun addRecipe(){
    var aRecipe = RecipeModel()

    if (recipeView.addRecipeData(aRecipe))
        recipes.create(aRecipe)
    else
        logger.info("Recipe Not Added")
}

fun updateRecipe() {
    recipeView.listRecipes(recipes)
    var searchId = recipeView.getId()
    val aRecipe = search(searchId)

    if(aRecipe != null) {
        if(recipeView.updateRecipeData(aRecipe)) {
            recipes.update(aRecipe)
            recipeView.showRecipe(aRecipe)
            logger.info("Recipe Updated : [ $aRecipe ]")
        }
        else
            logger.info("Recipe Not Updated")
    }
    else
        println("Recipe Not Updated...")

}

//fun listRecipes() {
//    println("List All Recipes")
//    println()
//    recipes.forEach { logger.info("${it}") }
//    println()
//}

fun searchRecipe() {
    val aRecipe = search(recipeView.getId())!!
    recipeView.showRecipe(aRecipe)
}

//fun getId() : Long {
//    var strId : String? // String to hold user input
//    var searchId : Long // Long to hold converted id
//    print("Enter id to Search/Update : ")
//    strId = readLine()!!
//    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
//        strId.toLong()
//    else
//        -9
//    return searchId
//}

fun search(id: Long) : RecipeModel? {
    var foundRecipe = recipes.findOne(id)
    return foundRecipe
}

fun dummyData() {
    recipes.create(RecipeModel(title = "New York New York", ingred = "So Good They Named It Twice"))
    recipes.create(RecipeModel(title= "Ring of Kerry", ingred = "Some place in the Kingdom"))
    recipes.create(RecipeModel(title = "Waterford City", ingred = "You get great Blaas Here!!"))
}