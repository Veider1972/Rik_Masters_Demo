package ru.veider.data.local

import io.realm.kotlin.types.RealmObject
import ru.veider.core.datatype.Transport
import kotlin.reflect.KClass

interface LocalRepo {
	suspend fun <T : RealmObject> getAll(model: KClass<T>) : Transport<List<T>>
	suspend fun <T : RealmObject> saveAll(objs: List<T>): Transport<Boolean>
	suspend fun <T : RealmObject> update(obj: T): Transport<Boolean>
	suspend fun <T : RealmObject> save(obj: T): Transport<Boolean>
}