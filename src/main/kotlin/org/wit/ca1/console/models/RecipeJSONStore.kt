package org.wit.ca1.console.models
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.ca1.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "recipes.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<RecipeModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class RecipeJSONStore : RecipeStore {

    var recipes = mutableListOf<RecipeModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<RecipeModel> {
        return recipes
    }

    override fun findOne(id: Long) : RecipeModel? {
        var foundRecipe: RecipeModel? = recipes.find { p -> p.id == id }
        return foundRecipe
    }

    override fun create(recipe: RecipeModel) {
        recipe.id = generateRandomId()
        recipes.add(recipe)
        serialize()
    }

    override fun update(recipe: RecipeModel) {
        var foundRecipe = findOne(recipe.id!!)
        if (foundRecipe != null) {
            foundRecipe.title = recipe.title
            foundRecipe.ingred = recipe.ingred
        }
        serialize()
    }

    internal fun logAll() {
        recipes.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(recipes, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        recipes = Gson().fromJson(jsonString, listType)
    }

    //new
    override fun delete(recipe: RecipeModel) {
        recipes.remove(recipe)
        serialize()
    }
}