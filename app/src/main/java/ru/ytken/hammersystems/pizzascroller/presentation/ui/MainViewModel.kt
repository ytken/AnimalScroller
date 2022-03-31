package ru.ytken.hammersystems.pizzascroller.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ytken.hammersystems.pizzascroller.domain.interactor.GetListOfDishesUseCase
import ru.ytken.hammersystems.pizzascroller.presentation.mapper.DishesMapper
import ru.ytken.hammersystems.pizzascroller.presentation.model.DishParam
import ru.ytken.hammersystems.pizzascroller.presentation.model.TypesListParam

class MainViewModel(val getListOfDishesUseCase: GetListOfDishesUseCase): ViewModel() {

    private val liveListTypes = MutableLiveData(TypesListParam())
    val listTypes: LiveData<TypesListParam> = liveListTypes

    private val liveListDishes = MutableLiveData<List<DishParam>>()
    val listDishes: LiveData<List<DishParam>> = liveListDishes

    private var pNumberMenu = MutableLiveData(0)
    val numberMenu: LiveData<Int> = pNumberMenu

    private val mapper = DishesMapper()

    init {
        liveListTypes.value?.initList()
        setPNumberMenu(0)
    }

    fun get(number: Int) = viewModelScope.launch {
        val response = getListOfDishesUseCase.execute(number)
        liveListDishes.value = response.body()?.let { mapper.mapListDishesGetParamToDishParam(it) }
    }

    fun changeMenuNumber(type: String) {
        if (liveListTypes.value != null) {
            val number = liveListTypes.value!!.list.indexOf(type)
            if (number != pNumberMenu.value) {
                setPNumberMenu(number)
            }
        }
    }

    fun setPNumberMenu(number: Int) {
        pNumberMenu.value = number
        get(number + 1)
    }

}