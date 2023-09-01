package ru.veider.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.bind
import org.koin.dsl.module
import ru.veider.data.Repo
import ru.veider.data.RepoImpl

val dataModule = module {
	singleOf(::RepoImpl) { bind<Repo>() }
}