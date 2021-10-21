package org.wit.ca1.console.views

import org.wit.ca1.console.models.RecipeJSONStore
import org.wit.ca1.console.models.RecipeModel

class RecipeView {

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

    fun listRecipes(recipes : RecipeJSONStore) {
        println("List All Recipes")
        println()
        recipes.logAll()
        println()
    }

    fun showRecipe(recipe : RecipeModel) {
        if(recipe != null)
            println("Recipe Details [ $recipe ]")
        else
            println("Recipe Not Found...")
    }

    fun addRecipeData(recipe : RecipeModel) : Boolean {
        print("Enter a Title : ")
        recipe.title = readLine()!!
        print("Enter Ingredients : ")
        recipe.ingred = readLine()!!

        return recipe.title.isNotEmpty() && recipe.ingred.isNotEmpty()
    }

    fun updateRecipeData(recipe : RecipeModel) : Boolean {

        var tempTitle: String?
        var tempIngred: String?

        if (recipe != null) {
            print("Enter a new Title for [ " + recipe.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter new Ingredients for [ " + recipe.ingred + " ] : ")
            tempIngred = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempIngred.isNullOrEmpty()) {
                recipe.title = tempTitle
                recipe.ingred = tempIngred
                return true
            }
        }
        return false
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
}