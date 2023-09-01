package ru.veider.data.local.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId

class CameraRealm():RealmObject {

	@PrimaryKey
	var objID:String = BsonObjectId().toHexString()
	var name : String = ""
	var localName : String? = null
	var snapshot : String? = null
	var room : String? = null
	var id : Int = 0
	var favorites : Boolean = false
	var rec : Boolean = false

	constructor(
		objID: String?,
		name: String,
		localName: String?,
		snapshot: String?,
		room: String?,
		id: Int,
		favorites: Boolean,
		rec: Boolean
	) : this() {
		this.objID = objID ?: BsonObjectId().toHexString()
		this.name = name
		this.localName = localName
		this.snapshot = snapshot
		this.room = room
		this.id = id
		this.favorites = favorites
		this.rec = rec
	}
}