package ru.veider.data

import io.realm.kotlin.types.RealmObject
import ru.veider.core.datatype.Transport
import ru.veider.data.local.LocalRepo
import ru.veider.data.remote.RemoteRepo
import ru.veider.data.remote.model.CamerasBaseResponse
import ru.veider.data.remote.model.DoorsBaseResponse
import kotlin.reflect.KClass

class RepoImpl(
	private val remoteRepo: RemoteRepo,
	private val localRepo: LocalRepo
):Repo {
	override suspend fun getDoors(): Transport<DoorsBaseResponse> =
		remoteRepo.getDoors()

	override suspend fun getCameras(): Transport<CamerasBaseResponse> =
		remoteRepo.getCameras()

	override fun closeRepo() {
		remoteRepo.closeRepo()
	}

	override suspend fun <T : RealmObject> getAll(model: KClass<T>): Transport<List<T>> =
		localRepo.getAll(model)

	override suspend fun <T : RealmObject> saveAll(objs: List<T>): Transport<Boolean> =
		localRepo.saveAll(objs)

	override suspend fun <T : RealmObject> update(obj: T): Transport<Boolean> = localRepo.update(obj)

	override suspend fun <T : RealmObject> save(obj: T): Transport<Boolean> = localRepo.save(obj)

}