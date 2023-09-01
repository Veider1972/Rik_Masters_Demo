package ru.veider.data.local

import io.realm.kotlin.types.RealmObject
import ru.veider.core.datatype.Transport
import ru.veider.data.local.db.RealmDataStorage
import kotlin.reflect.KClass

class LocalRepoImpl(
	private val ds: RealmDataStorage
) : LocalRepo {

	override suspend fun <T : RealmObject> getAll(model: KClass<T>) = ds.fetch(model = model)
	override suspend fun <T : RealmObject> saveAll(objs: List<T>) = ds.saveAll(objs)
	override suspend fun <T : RealmObject> update(obj: T): Transport<Boolean> =ds.update(obj)
	override suspend fun <T : RealmObject> save(obj: T): Transport<Boolean> =ds.save(obj)

}