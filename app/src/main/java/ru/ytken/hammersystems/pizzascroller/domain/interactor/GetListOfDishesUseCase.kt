package ru.ytken.hammersystems.pizzascroller.domain.interactor

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import ru.ytken.hammersystems.pizzascroller.data.model.DishGetParam
import ru.ytken.hammersystems.pizzascroller.domain.repository.ParameterRepository

class GetListOfDishesUseCase(private val parameterRepository: ParameterRepository) {

    fun execute(number: Int):
            Single<List<DishGetParam>> = parameterRepository.listDishesGet(number)

}