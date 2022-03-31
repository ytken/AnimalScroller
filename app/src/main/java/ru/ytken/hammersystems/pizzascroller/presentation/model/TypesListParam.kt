package ru.ytken.hammersystems.pizzascroller.presentation.model

import android.content.Context
import ru.ytken.hammersystems.pizzascroller.R

data class TypesListParam(
    val list: ArrayList<String> = ArrayList()
) {
    fun initList() {
        val arrayTypes = arrayOf("Дай 1","Дай 2","Дай 3","Дай 4")
        for (el in arrayTypes)
            list.add(el)
    }
}