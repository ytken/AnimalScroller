package ru.ytken.hammersystems.pizzascroller.presentation.mapper

import ru.ytken.hammersystems.pizzascroller.data.model.DishGetParam
import ru.ytken.hammersystems.pizzascroller.presentation.model.DishParam

fun List<DishGetParam>.mapListDishesGetParamToDishParam(): List<DishParam> {
        return this.map {
            DishParam(
                name = it.name,
                description = it.diet,
                image = it.image_link,
                buttonLabel = it.id.toString()
            )
        }
    }