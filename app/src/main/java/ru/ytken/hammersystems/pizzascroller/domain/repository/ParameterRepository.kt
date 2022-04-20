package ru.ytken.hammersystems.pizzascroller.domain.repository

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import ru.ytken.hammersystems.pizzascroller.data.model.DishGetParam

interface ParameterRepository {

    fun listDishesGet(number: Int) : Single<List<DishGetParam>>

}