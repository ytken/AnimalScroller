package ru.ytken.hammersystems.pizzascroller.presentation.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.item_main_list.view.*
import kotlinx.android.synthetic.main.item_types_list.view.*
import ru.ytken.hammersystems.pizzascroller.R
import ru.ytken.hammersystems.pizzascroller.presentation.helper.NetworkHelper
import ru.ytken.hammersystems.pizzascroller.presentation.model.DishParam
import ru.ytken.hammersystems.pizzascroller.presentation.ui.MainViewModel

class MenuFragment: Fragment(R.layout.fragment_menu) {

    private val vm: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        linearLayoutPromo.addView(layoutInflater.inflate(R.layout.promo_banner, null))
        linearLayoutPromo.addView(layoutInflater.inflate(R.layout.promo_banner, null))

        vm.listTypes.observe(viewLifecycleOwner) {
            linearLayoutMenu.removeAllViews()
            for (el in it.list)
                linearLayoutMenu.addTypeItem(createTypeItem(el))
        }

        progressBarLoadRecycler.visibility = View.VISIBLE

        mainRecyclerView.layoutManager = LinearLayoutManager(context)
        mainRecyclerView.adapter = context?.let { CustomRecyclerAdapter(it, null) }
        vm.listDishes.observe(viewLifecycleOwner) { it1 ->
            progressBarLoadRecycler.visibility = View.GONE
            mainRecyclerView.adapter = context?.let { it ->
                CustomRecyclerAdapter(it, it1)
            }
        }
    }

    private fun LinearLayout.addTypeItem(
        typeView: MaterialCardView
    ) {
        typeView.setOnClickListener {
            for (child in this.children) {
                if ((child as MaterialCardView).isChecked)
                    child.changeCheckedStatus()
            }
            val card = it as MaterialCardView
            card.changeCheckedStatus()
        }
        this.addView(typeView)
        if (this.indexOfChild(typeView) == vm.numberMenu.value)
            typeView.changeCheckedStatus()
    }

    private fun MaterialCardView.changeCheckedStatus() {
        this.toggle()
        context?.let { it ->
            if(this.isChecked)
                this.textViewType.setTextColor(it.getColor(R.color.type_card_checked_text))
            else
                this.textViewType.setTextColor(it.getColor(R.color.inactive_text_gray))
        }
    }

    private fun createTypeItem(type: String): MaterialCardView {
        val typeView = layoutInflater.inflate(R.layout.item_types_list, null) as MaterialCardView
        typeView.textViewType.text = type
        typeView.setOnCheckedChangeListener { card, isChecked ->
            if (isChecked)
                if (context?.let { NetworkHelper.isNetworkConnected(it) } != true) {
                    Toast.makeText(activity, "Необходимо интернет соединение", Toast.LENGTH_SHORT)
                        .show()
                    progressBarLoadRecycler.visibility = View.GONE
                }
                else
                    vm.changeMenuNumber(card.textViewType.text.toString())
        }
        return typeView
    }

}

class CustomRecyclerAdapter(context: Context, listDishes: List<DishParam>?) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {
    val mContext = context
    val mListDishes = listDishes
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_main_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (mListDishes != null) {
            val view = holder.itemView
            view.textViewHeading.text = mListDishes[position].name
            view.textViewDescription.text = "Diet: \n${mListDishes[position].description}"
            view.buttonShowPrice.text = mListDishes[position].buttonLabel
            Glide.with(mContext).load(mListDishes[position].image).into(view.imageViewIcon)
        }
    }

    override fun getItemCount(): Int {
        return mListDishes?.size ?: 0
    }

}