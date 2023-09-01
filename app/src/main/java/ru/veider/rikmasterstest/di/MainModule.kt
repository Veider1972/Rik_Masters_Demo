package ru.veider.rikmasterstest.ru.veider.rikmasterstest.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.veider.rikmasterstest.ru.veider.rikmasterstest.viewmodels.MainViewModel

val mainModule = module {
	singleOf(::MainViewModel)
}