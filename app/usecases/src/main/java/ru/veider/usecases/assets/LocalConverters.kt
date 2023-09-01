package ru.veider.usecases.assets

import ru.veider.data.local.model.CameraRealm
import ru.veider.data.local.model.DoorRealm
import ru.veider.data.local.model.RoomRealm
import ru.veider.domain.Camera
import ru.veider.domain.Door
import ru.veider.domain.Room

fun CameraRealm.toCamera(): Camera =
	Camera(
		objID = this.objID,
		name = this.name,
		localName = this.localName,
		snapshot = this.snapshot,
		room = this.room,
		id = this.id,
		favorites = this.favorites,
		rec = this.rec
	)

fun Camera.toCameraReam(): CameraRealm =
	CameraRealm(
		objID = this.objID,
		name = this.name,
		localName = this.localName,
		snapshot = this.snapshot,
		room = this.room,
		id = this.id,
		favorites = this.favorites,
		rec = this.rec
	)

fun DoorRealm.toDoor(): Door =
	Door(
		objID = this.objID,
		name = this.name,
		localName = this.localName,
		snapshot = this.snapshot,
		room = this.room,
		id = this.id,
		favorites = this.favorites,
		locked = this.locked
	)

fun Door.toDoorRealm(): DoorRealm =
	DoorRealm(
		objID = this.objID,
		name = this.name,
		localName = this.localName,
		snapshot = this.snapshot,
		room = this.room,
		id = this.id,
		favorites = this.favorites,
		locked = this.locked
	)

fun RoomRealm.toRoom(): Room =
	Room(
		objID = this.objID,
		name = this.name,
		localName = this.localName
	)

fun Room.toRoomRealm(): RoomRealm =
	RoomRealm(
		objID = this.objID,
		name = this.name,
		localName = this.localName
	)