package ru.ytken.hammersystems.pizzascroller.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.main_container.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ytken.hammersystems.pizzascroller.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_container)
        vm
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                    as NavHostFragment
        navController = navHostFragment.navController
        bottom_nav_view.setupWithNavController(navController)
    }
}