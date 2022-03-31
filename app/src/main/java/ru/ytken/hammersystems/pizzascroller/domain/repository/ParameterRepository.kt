package ru.ytken.hammersystems.pizzascroller.domain.repository

import retrofit2.Response
import ru.ytken.hammersystems.pizzascroller.data.model.DishGetParam

interface ParameterRepository {

    suspend fun listDishesGet(number: Int) : Response<List<DishGetParam>>

}