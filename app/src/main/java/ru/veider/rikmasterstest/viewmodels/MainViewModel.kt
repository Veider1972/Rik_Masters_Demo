package ru.veider.rikmasterstest.ru.veider.rikmasterstest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.veider.core.datatype.ScreenState
import ru.veider.core.datatype.Transport
import ru.veider.domain.Camera
import ru.veider.domain.Door
import ru.veider.domain.Room
import ru.veider.domain.getBy
import ru.veider.usecases.UseCases

class MainViewModel(
	private val useCases: UseCases
) : ViewModel() {

	private val _doorsList = MutableStateFlow<ScreenState<List<Door>>>(ScreenState.Success(emptyList()))
	val doorsList: StateFlow<ScreenState<List<Door>>> get() = _doorsList.asStateFlow()

	private val _camerasList = MutableStateFlow<ScreenState<List<Camera>>>(ScreenState.Success(emptyList()))
	val camerasList: StateFlow<ScreenState<List<Camera>>> get() = _camerasList.asStateFlow()

	private val _roomsList = MutableStateFlow<List<Room>>(emptyList())
	val roomsList: StateFlow<List<Room>> get() = _roomsList.asStateFlow()

	private val _isDoorsRefreshing = MutableStateFlow(false)
	val isDoorsRefreshing: StateFlow<Boolean> get() = _isDoorsRefreshing

	private val _isCamerasRefreshing = MutableStateFlow(false)
	val isCamerasRefreshing: StateFlow<Boolean> get() = _isCamerasRefreshing

	private val _revealedCameraCard = MutableStateFlow<Camera?>(null)
	val revealedCameraCard: StateFlow<Camera?> get() = _revealedCameraCard

	private val _revealedDoorCard = MutableStateFlow<Door?>(null)
	val revealedDoorCard: StateFlow<Door?> get() = _revealedDoorCard

	private val _revealedRoomCard = MutableStateFlow<Room?>(null)
	val revealedRoomCard: StateFlow<Room?> get() = _revealedRoomCard

	init {
		loadAllCameras()
		loadAllDoors()
	}

	override fun onCleared() {
		useCases.closeRepo()
		super.onCleared()
	}

	fun getRemoteDoors() {
		_doorsList.value = ScreenState.Loading()
		_isDoorsRefreshing.value = true
		viewModelScope.launch {
			withContext(Dispatchers.IO) {
				when (val result = useCases.getRemoteDoors()) {
					is Transport.Success -> {
						_doorsList.emit(ScreenState.Success(result.data))
						_isDoorsRefreshing.emit(false)
						saveAllDoors(result.data)
					}

					is Transport.Error -> {
						_doorsList.emit(ScreenState.Error(result.error))
						_isDoorsRefreshing.emit(false)
					}
				}
			}

		}
	}

	fun getRemoteCameras() {
		_camerasList.value = ScreenState.Loading()
		_isCamerasRefreshing.value = true
		viewModelScope.launch {
			withContext(Dispatchers.IO) {
				when (val result = useCases.getRemoteCameras()) {
					is Transport.Success -> {
						_camerasList.emit(ScreenState.Success(result.data.second))
						_roomsList.emit(result.data.first)
						_isCamerasRefreshing.emit(false)
						saveAllCameras(result.data.second, result.data.first)
					}

					is Transport.Error -> {
						_camerasList.emit(ScreenState.Error(result.error))
						_isCamerasRefreshing.emit(false)
					}
				}
			}
		}
	}

	fun revealCamera(camera: Camera?) {
		_revealedCameraCard.value = camera
		_revealedRoomCard.value = null
	}

	fun revealDoor(door: Door?) {
		_revealedDoorCard.value = door
	}

	fun revealRoom(room: Room?) {
		_revealedRoomCard.value = room
		_revealedCameraCard.value = null
	}

	private fun loadAllCameras(lightMode: Boolean = false) {
		if (lightMode.not())
			_camerasList.value = ScreenState.Loading()
		viewModelScope.launch {
			withContext(Dispatchers.IO) {
				val rooms = async { useCases.getAllRooms() }.await()
				if (rooms is Transport.Success)
					_roomsList.emit((rooms).data)
				when (val result = useCases.getAllCameras()) {
					is Transport.Success -> {
						if (result.data.isNotEmpty())
							_camerasList.emit(ScreenState.Success(result.data))
						else
							getRemoteCameras()
					}

					is Transport.Error -> {
						getRemoteCameras()
					}
				}
			}
		}
	}

	private fun loadAllDoors(lightMode: Boolean = false) {
		if (lightMode.not())
			_doorsList.value = ScreenState.Loading()
		viewModelScope.launch {
			withContext(Dispatchers.IO) {
				when (val doors = async { useCases.getAllDoors() }.await()) {
					is Transport.Success -> {
						if (doors.data.isNotEmpty()) {
							_doorsList.emit(ScreenState.Success(doors.data))

						} else
							getRemoteDoors()
					}

					is Transport.Error -> {
						getRemoteDoors()
					}
				}
			}
		}
	}

	private fun saveAllCameras(cameras: List<Camera>, rooms: List<Room>) {
		viewModelScope.launch {
			withContext(Dispatchers.IO) {
				when (val cameraList = useCases.getAllCameras()) {
					is Transport.Success -> {
						cameras.forEach { camera ->
							try {
								val index = cameraList.data.getBy(camera.name, camera.id)
								val newCamera = cameraList.data[index].copy(
									room = camera.room,
									snapshot = camera.snapshot,
									favorites = camera.favorites,
									rec = camera.rec
								)
								useCases.updateCamera(newCamera)
							} catch (t: Throwable) {
								useCases.saveCamera(camera)
							}
						}
					}

					is Transport.Error -> {
						useCases.saveAllCameras(cameras)
					}
				}
				when (val roomList = useCases.getAllRooms()) {
					is Transport.Success -> {
						rooms.forEach { room ->
							try {
								roomList.data.getBy(room.name)
							} catch (t: Throwable) {
								useCases.saveRoom(room)
							}
						}
					}

					is Transport.Error -> {
						useCases.saveAllRooms(rooms)
					}
				}
				loadAllCameras()
			}
		}
	}

	private fun saveAllDoors(doors: List<Door>) {
		viewModelScope.launch {
			withContext(Dispatchers.IO) {
				when (val doorList = useCases.getAllDoors()) {
					is Transport.Success -> {
						doors.forEach { door ->
							try {
								val index = doorList.data.getBy(door.name, door.id)
								val newDoor = doorList.data[index].copy(
									room = door.room,
									snapshot = door.snapshot,
									favorites = door.favorites,
									locked = door.locked
								)
								useCases.updateDoor(newDoor)
							} catch (t: Throwable) {
								useCases.saveDoor(door)
							}
						}
					}

					is Transport.Error -> {
						useCases.saveAllDoors(doors)
					}
				}
				loadAllDoors()
			}
		}
	}

	fun updateCamera(camera: Camera) {
		viewModelScope.launch {
			withContext(Dispatchers.IO) {
				when (useCases.updateCamera(camera)) {
					is Transport.Success -> {
						loadAllCameras(lightMode = true)
						revealCamera(null)
					}

					is Transport.Error -> {}
				}
			}
		}
	}

	fun updateRoom(room: Room) {
		viewModelScope.launch {
			withContext(Dispatchers.IO) {
				when (useCases.updateRoom(room)) {
					is Transport.Success -> {
						loadAllCameras(lightMode = true)
						revealRoom(null)
					}

					is Transport.Error -> {}
				}
			}
		}
	}

	fun updateDoor(door: Door) {
		viewModelScope.launch {
			withContext(Dispatchers.IO) {
				when (useCases.updateDoor(door)) {
					is Transport.Success -> {
						loadAllDoors(lightMode = true)
						revealDoor(null)
					}

					is Transport.Error -> {}
				}
			}
		}
	}
}