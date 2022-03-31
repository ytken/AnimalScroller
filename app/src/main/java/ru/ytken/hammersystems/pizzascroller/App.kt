package ru.ytken.hammersystems.pizzascroller

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.ytken.hammersystems.pizzascroller.di.appModule
import ru.ytken.hammersystems.pizzascroller.di.dataModule
import ru.ytken.hammersystems.pizzascroller.di.domainModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(
                appModule,
                dataModule,
                domainModule
            ))
        }
    }

}