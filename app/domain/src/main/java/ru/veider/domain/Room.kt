package ru.veider.domain

data class Room (
	val objID: String? = null,
	val name: String,
	val localName: String?
)

fun List<Room>.getBy(name: String):Int{
	for (i in this.indices){
		if (this[i].name==name)
			return i
	}
	throw Throwable("Element not found")
}