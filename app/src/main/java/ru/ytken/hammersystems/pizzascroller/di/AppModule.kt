package ru.ytken.hammersystems.pizzascroller.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.ytken.hammersystems.pizzascroller.presentation.ui.MainViewModel

val appModule = module {

    viewModel<MainViewModel> {
        MainViewModel(
            getListOfDishesUseCase = get()
        )
    }

}