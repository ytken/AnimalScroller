package ru.ytken.hammersystems.pizzascroller.domain.interactor

import retrofit2.Response
import ru.ytken.hammersystems.pizzascroller.data.model.DishGetParam
import ru.ytken.hammersystems.pizzascroller.domain.repository.ParameterRepository

class GetListOfDishesUseCase(private val parameterRepository: ParameterRepository) {

    suspend fun execute(number: Int):
            Response<List<DishGetParam>> = parameterRepository.listDishesGet(number)

}