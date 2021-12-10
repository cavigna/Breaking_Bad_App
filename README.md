<h1 align = "center">Breaking Bad App</h1>
<p align = "center">
<image src="./images/bb_logo.jpg" height= "350px" />
</p>




<p align = "center">
<image src="./images/bb.gif" height= "750px" />
</p>


Esta es una app muy  que utiliza  [The Breaking Bad Api](https://breakingbadapi.com/) como fuente de datos remota. Cuando se inicia por primera vez, hace las siguientes llamadas:

* Listado de todos los personajes
* Listado de todos los episiodios
* Listado de todas las frases
* Listado de todas las muertes

```kotlin
interface ApiService {

    @GET("quotes")
    suspend fun listadoFrasesApi(): List<Quotes>

    @GET("characters")
    suspend fun listadoPersonajesApi(): List<Personaje>

    @GET("episodes")
    suspend fun listadoEpisodiosApi(): List<Episodio>

    @GET("deaths")
    suspend fun listadoMuertesApi(): List<Muertes>

}
```

Estos datos son almacenados en una base de datos local, por medio de Jetpack Room, en una serie de tablas. Defino en el repositorio la siguiente función:

```kotlin
    suspend fun agregarDB(){
        dao.agregarListadoFrases(listadoFrasesApi())
        dao.agregarListadoPersonajes(listadoPersonajesApi())
        dao.agregarListadoEpisodios(listadoEpisodiosApi())
        dao.agregarListadoMuertes(listadoMuertesApi())
    }
```

Room no acepta estructura de datos complejos, por ende, si quiero introducir un listado de String, tengo que utilizar los siguientes conversores:
1. De listado a String
2.  De string a Listado
```kotlin
class Converters {

    @TypeConverter

    fun fromListadoAString(listado: List<String?>):String?{
        return listado.joinToString {
            it.toString()
        }
    }

    @TypeConverter
    fun fromStringToListado(string: String):List<String>{
        return string.split(",")
    }

    @TypeConverter
    fun fromListadoIntToString(list: List<Int>):String{
        return list.joinToString {
            it.toString()
        }
    }

    @TypeConverter
    fun fromStringToListadoInt(string: String?):List<Int?>?{
        val numeros = string?.split(",")

        return numeros?.map { it.toIntOrNull() }
    }
}

```

Luego, la aplicación consume los datos de forma local, haciendo las llamadas a la base de datos y no al servidor remoto. Esta elección de diseño se debe al reducido tamaño de la fuenre remota, por lo que consideré el mejor camino a seguir. Aparte, permite que la aplicación pueda funcionar sin internet.