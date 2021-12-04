package com.example.breakingbadapp.viewmodel

import androidx.lifecycle.*
import com.example.breakingbadapp.model.Personaje
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class BbViewModel(private val repositorio: Repositorio) : ViewModel(){

    val fraseRandom = repositorio.fraseRandom()

    private var _personajeRandom: MutableLiveData<Personaje> = MutableLiveData(Personaje())

    //val personajeRandom: LiveData<Personaje> = _personajeRandom
    val personajeRandom: LiveData<Personaje> = repositorio.personajeRandmom((1..62).random()).asLiveData()

    val listadoPersonaje = repositorio.listadoPersonajes().asLiveData()

    val listadoFrases = repositorio.listadoFrases().asLiveData()

    val listadoMuertes = repositorio.listadoMuertes().asLiveData()

    init {
        _personajeRandom.postValue(repositorio.personajeRandmom(1).asLiveData().value)
        viewModelScope.launch() {


            repositorio.agregarDB()
            //repositorio.agregarDB()
            _personajeRandom.postValue(repositorio.personajeRandmom(1).asLiveData().value)
        }
    }
}

class BbModelFactory(private val repositorio: Repositorio) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BbViewModel(repositorio) as T
    }
}