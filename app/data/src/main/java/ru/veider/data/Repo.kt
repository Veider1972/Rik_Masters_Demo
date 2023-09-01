package ru.veider.data

import ru.veider.data.local.LocalRepo
import ru.veider.data.remote.RemoteRepo

interface Repo : RemoteRepo, LocalRepo