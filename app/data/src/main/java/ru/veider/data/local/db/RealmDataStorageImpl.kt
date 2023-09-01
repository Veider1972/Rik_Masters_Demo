package ru.veider.data.local.db

import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.types.RealmObject
import ru.veider.core.datatype.Transport
import ru.veider.data.local.assets.Sorted
import kotlin.reflect.KClass


class RealmDataStorageImpl(private val realm: Realm) : RealmDataStorage {

	override suspend fun <T : RealmObject> create(model: T): Transport<T> =
		realm.write {
			try {
				val obj = copyToRealm(model, UpdatePolicy.ALL)
				Transport.Success(obj)
			} catch (e: Exception) {
				Transport.Error(e)
			}
		}

	override suspend fun <T : RealmObject> save(obj: T): Transport<Boolean> =
		realm.write {
			try {
				copyToRealm(obj, UpdatePolicy.ALL)
				Transport.Success(true)
			} catch (e: Exception) {
				Transport.Error(e)
			}
		}

	override suspend fun <T : RealmObject> saveAll(objs: List<T>): Transport<Boolean> =
		realm.write {
			try {
				for (obj in objs) {
					copyToRealm(obj, UpdatePolicy.ALL)
				}
				Transport.Success(true)
			} catch (e: Exception) {
				Transport.Error(e)
			}
		}

	override suspend fun <T : RealmObject> update(obj: T): Transport<Boolean> =
		realm.write {
			try {
				copyToRealm(obj, UpdatePolicy.ALL)
				Transport.Success(true)
			} catch (e: Exception) {
				Transport.Error(e)
			}

		}

	override suspend fun <T : RealmObject> delete(obj: T): Transport<Boolean> =
		realm.write {
			try {
				delete(obj)
				Transport.Success(true)
			} catch (e: Exception) {
				Transport.Error(e)
			}

		}

	override suspend fun <T : RealmObject> deleteAll(model: KClass<T>): Transport<Boolean> =
		realm.write {
			try {
				delete(model)
				Transport.Success(true)
			} catch (e: Exception) {
				Transport.Error(e)
			}

		}

	override suspend fun <T : RealmObject> fetch(
		model: KClass<T>,
		predicate: String?,
		sorted: Sorted?
	): Transport<List<T>> {
		return realm.write {
			try {
				var queryChain = query(model)
				predicate?.let {
					queryChain = queryChain.query(it)
				}
				sorted?.let {
					queryChain = queryChain.sort(it.key, it.ascending)
				}
				val results = queryChain.find().toList()
				if (results.isNotEmpty()) {
					Transport.Success(realm.copyFromRealm(results))
				} else {
					Transport.Error(Exception("Not found results"))
				}
			} catch (e: Exception) {
				Transport.Error(e)
			}
		}
	}
}