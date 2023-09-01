package ru.veider.rikmasterstest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.veider.data.di.dataModule
import ru.veider.data.local.di.realmModule
import ru.veider.data.remote.di.ktorModule
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.di.mainModule
import ru.veider.usecases.di.useCasesModule

class MainApp: Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger()
			androidContext(this@MainApp)
			modules(mainModule, useCasesModule, dataModule, realmModule, ktorModule)
		}
	}
}