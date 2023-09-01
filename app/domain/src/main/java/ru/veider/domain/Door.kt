package ru.veider.domain

data class Door(

	val objID: String? = null,
	val name : String,
	val localName : String? = null,
	val snapshot : String? = null,
	val room : String? = null,
	val id : Int,
	val favorites : Boolean,
	val locked : Boolean
)

fun List<Door>.getBy(name: String, id: Int):Int{
	for (i in this.indices){
		if (this[i].name==name && this[i].id==id)
			return i
	}
	throw Throwable("Element not found")
}
