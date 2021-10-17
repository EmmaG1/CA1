package org.wit.ca1.console.models

interface RecipeStore {
        fun findAll(): List<RecipeModel>
        fun findOne(id: Long): RecipeModel?
        fun create(recipe: RecipeModel)
        fun update(recipe: RecipeModel)
}