package ru.ytken.hammersystems.pizzascroller.di

import org.koin.dsl.module
import ru.ytken.hammersystems.pizzascroller.data.repository.ParameterRepositoryImpl
import ru.ytken.hammersystems.pizzascroller.domain.repository.ParameterRepository

val dataModule = module {

    single<ParameterRepository> {
        ParameterRepositoryImpl(context = get())
    }

}