package ru.ytken.hammersystems.pizzascroller.data.repository

import android.content.Context
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import ru.ytken.hammersystems.pizzascroller.data.model.DishGetParam
import ru.ytken.hammersystems.pizzascroller.data.network.ApiStorage
import ru.ytken.hammersystems.pizzascroller.domain.repository.ParameterRepository

class ParameterRepositoryImpl(context: Context): ParameterRepository {

    override fun listDishesGet(number: Int): Single<List<DishGetParam>>  =
        ApiStorage.api.get(number)

}