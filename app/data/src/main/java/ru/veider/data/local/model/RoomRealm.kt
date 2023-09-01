package ru.veider.data.local.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId

class RoomRealm() : RealmObject {
	@PrimaryKey
	var objID: String = BsonObjectId().toHexString()
	var name: String = ""
	var localName: String? = null

	constructor(
		objID: String?,
		name: String,
		localName: String?
	): this() {
		this.objID = objID ?: BsonObjectId().toHexString()
		this.name = name
		this.localName = localName
	}
}

