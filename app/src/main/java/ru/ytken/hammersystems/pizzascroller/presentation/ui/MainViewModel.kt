package ru.ytken.hammersystems.pizzascroller.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.ytken.hammersystems.pizzascroller.domain.interactor.GetListOfDishesUseCase
import ru.ytken.hammersystems.pizzascroller.presentation.mapper.mapListDishesGetParamToDishParam
import ru.ytken.hammersystems.pizzascroller.presentation.model.DishParam
import ru.ytken.hammersystems.pizzascroller.presentation.model.TypesListParam

class MainViewModel(val getListOfDishesUseCase: GetListOfDishesUseCase): ViewModel() {

    private val TAG = MainViewModel::class.simpleName

    private val liveListTypes = MutableLiveData(TypesListParam())
    val listTypes: LiveData<TypesListParam> = liveListTypes

    private val liveListDishes = MutableLiveData<List<DishParam>>()
    val listDishes: LiveData<List<DishParam>> = liveListDishes

    private var pNumberMenu = MutableLiveData(0)
    val numberMenu: LiveData<Int> = pNumberMenu

    init {
        liveListTypes.value?.initList()
        setPNumberMenu(0)
    }

    fun get(number: Int) {
        val single = getListOfDishesUseCase.execute(number)
        single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {it ->
                liveListDishes.value = it.mapListDishesGetParamToDishParam()
            }, { error ->
                Log.e(TAG, error.toString())
            })
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