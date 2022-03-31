package ru.ytken.hammersystems.pizzascroller.presentation.model

data class DishParam (
    var name: String,
    var description: String = "description",
    var image: String?,
    var buttonLabel: String
)