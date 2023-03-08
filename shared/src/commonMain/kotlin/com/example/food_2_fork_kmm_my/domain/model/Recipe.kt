package com.example.food_2_fork_kmm_my.domain.model

import kotlinx.datetime.LocalDateTime

/**
 * See Recipe example: https://food2fork.ca/
 */
data class Recipe(
    val id: Int,
    val title: String,
    val publisher: String,
    val featuredImage: String,
    val rating: Int,
    val sourceUrl: String,
    val ingredients: List<String> = listOf(),
    val dateAdded: LocalDateTime,
    val dateUpdated: LocalDateTime,
)

/*

Answer example

KtorTest {"pk":1551,"title":"Slow Cooker Beef and Barley Soup","publisher":"jessica",
"featured_image":"https://nyc3.digitaloceanspaces.com/food2fork/food2fork-static/featured_images/1551/featured_image.png",
"rating":41,"source_url":"http://picky-palate.com/2013/01/14/slow-cooker-beef-and-barley-soup/","description":"N/A",
"cooking_instructions":null,"ingredients":["8.8 ounces barley","1 cup chopped celery","1 pound stewing beef",
"1 teaspoon kosher salt","1 1/2 cups chopped onion","1/2 teaspoon kosher salt","1/2 cup all-purpose flour",
"1 small jalapeno (optional)","3 tablespoons minced garlic","1/4 cup chopped fresh parsley","2 cups sliced carrots, peeled",
"2 cups sliced cremini mushrooms","1/2 teaspoon ground black pepper","64 ounces reduced sodium beef broth","2-3 tablespoons Worcestershire Sauce",
"3 tablespoons extra virgin olive oil","1 medium zucchini, sliced and chopped","1/2 teaspoon freshly ground black pepper",
"2-3 tablespoons fresh chopped thyme leaves"],"date_added":"November 11 2020","date_updated":"November 11 2020",
"long_date_added":1606349126,"long_date_updated":1606349126}
*/