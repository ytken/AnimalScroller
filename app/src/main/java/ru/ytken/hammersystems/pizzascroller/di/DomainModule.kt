package ru.ytken.hammersystems.pizzascroller.di

import org.koin.dsl.module
import ru.ytken.hammersystems.pizzascroller.domain.interactor.GetListOfDishesUseCase

val domainModule = module {

    factory<GetListOfDishesUseCase> {
        GetListOfDishesUseCase(parameterRepository = get())
    }

}
