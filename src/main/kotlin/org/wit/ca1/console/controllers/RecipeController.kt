package org.wit.ca1.console.controllers

import mu.KotlinLogging
import org.wit.ca1.console.models.RecipeMemStore
import org.wit.ca1.console.models.RecipeModel
import org.wit.ca1.console.views.RecipeView

class RecipeController {

    val recipes = RecipeMemStore()
    val recipeView = RecipeView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Recipe Console App" }
        println("Recipe Kotlin App Version 1.0")
    }

    fun menu() :Int { return recipeView.menu() }

    fun add(){
        var aRecipe = RecipeModel()

        if (recipeView.addRecipeData(aRecipe))
            recipes.create(aRecipe)
        else
            logger.info("Recipe Not Added")
    }

    fun list() {
        recipeView.listRecipes(recipes)
    }

    fun update() {

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

    fun search() {
        val aRecipe = search(recipeView.getId())!!
        recipeView.showRecipe(aRecipe)
    }


    fun search(id: Long) : RecipeModel? {
        var foundRecipe = recipes.findOne(id)
        return foundRecipe
    }

    fun dummyData() {
        recipes.create(RecipeModel(title = "New York New York", ingred = "So Good They Named It Twice"))
        recipes.create(RecipeModel(title= "Ring of Kerry", ingred = "Some place in the Kingdom"))
        recipes.create(RecipeModel(title = "Waterford City", ingred = "You get great Blaas Here!!"))
    }
}