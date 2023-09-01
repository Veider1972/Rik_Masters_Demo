package ru.veider.data.local.di

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.veider.data.local.LocalRepo
import ru.veider.data.local.LocalRepoImpl
import ru.veider.data.local.db.RealmDataStorage
import ru.veider.data.local.db.RealmDataStorageImpl
import ru.veider.data.local.model.CameraRealm
import ru.veider.data.local.model.DoorRealm
import ru.veider.data.local.model.RoomRealm

private const val REALM_VERSION = 1L

val realmModule = module {

	fun realmConfiguration() = RealmConfiguration.Builder(
		setOf(
			CameraRealm::class,
			DoorRealm::class,
			RoomRealm::class
		)
	).schemaVersion(REALM_VERSION).build()

	singleOf(::realmConfiguration)

	fun realm(realmConfiguration: RealmConfiguration) = Realm.open(realmConfiguration)

	singleOf(::realm)
	singleOf(::RealmDataStorageImpl){bind<RealmDataStorage>()}
	singleOf(::LocalRepoImpl) { bind<LocalRepo>() }
}