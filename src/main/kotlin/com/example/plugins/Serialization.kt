package com.example.plugins

import io.ktor.serialization.jackson.*
import com.fasterxml.jackson.databind.*
import io.ktor.server.response.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

data class Recipe(val id: Int, val title: String, val image: String, val ingredients: List<String>, val instructions: String)

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    routing {
        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }
    }

    routing {
        get("/recipes") {
            val recipes = listOf(
                Recipe(
                    id = 1,
                    title = "Pesto Pasta",
                    image = "https://example.com/images/pesto_pasta.jpg",
                    ingredients = listOf(
                        "pasta",
                        "basil leaves",
                        "pine nuts",
                        "parmesan cheese",
                        "garlic",
                        "olive oil",
                        "salt",
                        "pepper"
                    ),
                    instructions = "1. Cook the pasta according to package instructions...\n2. In a food processor, combine basil leaves, pine nuts, garlic...\n3. Add parmesan cheese, salt, and pepper to the mixture...\n4. Drain the cooked pasta and toss it with the prepared pesto sauce...\n5. Serve hot and enjoy!"
                ),
                Recipe(
                    id = 2,
                    title = "Caprese Salad",
                    image = "https://example.com/images/caprese_salad.jpg",
                    ingredients = listOf(
                        "tomatoes",
                        "fresh mozzarella cheese",
                        "fresh basil leaves",
                        "balsamic vinegar",
                        "olive oil",
                        "salt",
                        "pepper"
                    ),
                    instructions = "1. Slice the tomatoes and fresh mozzarella cheese...\n2. Arrange the tomato and cheese slices on a plate...\n3. Place fresh basil leaves on top of the tomato and cheese slices...\n4. Drizzle balsamic vinegar and olive oil over the salad...\n5. Season with salt and pepper to taste...\n6. Serve and enjoy!"
                )
            )

            call.respond(mapOf("status" to "success", "message" to "Recipes retrieved successfully", "data" to mapOf("recipes" to recipes)))
        }
    }
}
