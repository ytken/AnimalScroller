package ru.ytken.hammersystems.pizzascroller.data.model

data class DishGetParam(
    var name: String,
    var latin_name: String,
    var animal_type: String,
    var active_time: String,
    var length_min: String,
    var length_max: String,
    var weight_min: String,
    var weight_max: String,
    var lifespan: String,
    var habitat: String,
    var diet: String,
    var geo_range: String,
    var image_link: String,
    var id: Int
)
