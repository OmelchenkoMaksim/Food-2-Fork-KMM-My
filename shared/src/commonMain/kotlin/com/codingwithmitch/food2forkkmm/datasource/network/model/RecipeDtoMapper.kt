package com.codingwithmitch.food2forkkmm.datasource.network.model

import com.codingwithmitch.food2forkkmm.domain.model.Recipe
import com.codingwithmitch.food2forkkmm.domain.util.DatetimeUtil
import com.codingwithmitch.food2forkkmm.domain.util.DomainMapper

class RecipeDtoMapper : DomainMapper<RecipeDto, Recipe> {

    private val datetimeUtil = DatetimeUtil()

    override fun mapToDomainModel(model: RecipeDto): Recipe {
        return Recipe(
            id = model.pk,
            title = model.title,
            featuredImage = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            ingredients = model.ingredients,
            dateAdded = datetimeUtil.toLocalDate(model.longDateAdded.toDouble()),
            dateUpdated = datetimeUtil.toLocalDate(model.longDateUpdated.toDouble()),
        )
    }

    override fun mapFromDomainModel(domainModel: Recipe): RecipeDto {
        return RecipeDto(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            ingredients = domainModel.ingredients,
            longDateAdded = datetimeUtil.toEpochMilliseconds(domainModel.dateAdded).toLong(),
            longDateUpdated = datetimeUtil.toEpochMilliseconds(domainModel.dateUpdated).toLong(),
        )
    }

    fun toDomainList(initial: List<RecipeDto>): List<Recipe>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Recipe>): List<RecipeDto>{
        return initial.map { mapFromDomainModel(it) }
    }


}