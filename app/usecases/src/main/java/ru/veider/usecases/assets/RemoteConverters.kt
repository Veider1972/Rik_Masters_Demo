package ru.veider.usecases.assets

import ru.veider.data.remote.model.CamerasBaseResponse
import ru.veider.data.remote.model.DoorsBaseResponse
import ru.veider.domain.Camera
import ru.veider.domain.Door
import ru.veider.domain.Room

fun DoorsBaseResponse.toDoorsList() =
	data.map {
		Door(
			name = it.name,
			localName = null,
			snapshot = it.snapshot,
			room = it.room,
			id = it.id,
			favorites = it.favorites,
			locked = false
		)
	}

fun CamerasBaseResponse.toCamerasList() =
	data.cameras.map {
		Camera(
			name = it.name,
			localName = null,
			snapshot = it.snapshot,
			room = it.room,
			id = it.id,
			favorites = it.favorites,
			rec = it.rec
		)
	}

fun CamerasBaseResponse.toRoomsList() =
	data.room.map{
		Room(
			name = it,
			localName = null
		)
	}