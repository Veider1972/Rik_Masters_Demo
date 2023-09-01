package ru.veider.data.remote.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.veider.data.remote.RemoteRepo
import ru.veider.data.remote.RemoteRepoImpl

val ktorModule = module{
	fun client() = HttpClient(Android) {
		install(ContentNegotiation) {
			json()
		}
	}

	singleOf(::client)

	singleOf(::RemoteRepoImpl) { bind<RemoteRepo>() }
}