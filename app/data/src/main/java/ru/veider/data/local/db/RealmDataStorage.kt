package ru.veider.data.local.db

import io.realm.kotlin.types.RealmObject
import ru.veider.core.datatype.Transport
import ru.veider.data.local.assets.Sorted
import kotlin.reflect.KClass

interface RealmDataStorage {
	suspend fun <T : RealmObject> create(model: T): Transport<T>
	suspend fun <T : RealmObject> save(obj: T): Transport<Boolean>
	suspend fun <T : RealmObject> saveAll(objs: List<T>): Transport<Boolean>
	suspend fun <T : RealmObject> update(obj: T): Transport<Boolean>
	suspend fun <T : RealmObject> delete(obj: T): Transport<Boolean>
	suspend fun <T : RealmObject> deleteAll(model: KClass<T>): Transport<Boolean>
	suspend fun <T : RealmObject> fetch(model: KClass<T>, predicate: String? = null, sorted: Sorted? = null): Transport<List<T>>
}