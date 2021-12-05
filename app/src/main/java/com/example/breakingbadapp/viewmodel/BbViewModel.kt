package com.example.breakingbadapp.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.breakingbadapp.model.Personaje
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class BbViewModel(private val repositorio: Repositorio) : ViewModel(){

    val fraseRandom = repositorio.fraseRandom()

    //private var _personajeRandom: MutableLiveData<Personaje> = MutableLiveData(Personaje())

    var terminoAgregarDB = MutableLiveData(false)

    //val personajeRandom: LiveData<Personaje> = _personajeRandom
    var personajeRandom: MutableLiveData<Personaje> =
        repositorio.personajeRandmom((1..62).random()).asLiveData() as MutableLiveData<Personaje>


    /*
    val personajeRandom: LiveData<Personaje> by lazy {
        repositorio.personajeRandmom((1..62).random()).asLiveData()
    }
     */

    val listadoPersonaje = repositorio.listadoPersonajes().asLiveData()
    val listadoPersonaje2 = repositorio.listadoPersonajes()

    val listadoFrases = repositorio.listadoFrases().asLiveData()

    val listadoMuertes = repositorio.listadoMuertes().asLiveData()

    //val conteoPersonaje = MutableLiveData(0)
    val conteoPersonaje = repositorio.conteoPersonadjes().asLiveData()



    init {

        viewModelScope.launch {
            //conteoPersonaje.postValue(repositorio.listadoPersonajes().count())
            //conteoPersonaje.postValue(repositorio.conteoPersonadjes().asLiveData().value)
//            if(repositorio.listadoPersonajes().count() !=62){
//                repositorio.agregarDB()
//            }
        }


    }

    fun agregarADb() {
        // AGREGAR VIEWMODELSCOPE Y CAMBIAR EN EL FRAGMENT

        viewModelScope.launch(IO) {
            repositorio.agregarDB()
            personajeRandom.postValue(repositorio.personajeRandmom((1..62).random()).asLiveData().value)
            terminoAgregarDB.postValue(true)
        }

    }

    fun buscarPersonaje(search:String) =  repositorio.buscarPersonaje(search).asLiveData()

    fun nuevoPersonajeRandom(id:Int)  = repositorio.personajeRandmom(id).asLiveData()



}

class BbModelFactory(private val repositorio: Repositorio) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BbViewModel(repositorio) as T
    }
}

/*
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
 */