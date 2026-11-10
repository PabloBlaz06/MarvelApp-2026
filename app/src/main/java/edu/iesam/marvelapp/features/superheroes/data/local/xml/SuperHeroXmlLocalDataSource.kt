package edu.iesam.marvelapp.features.superheroes.data.local.xml

import android.content.Context
import edu.iesam.marvelapp.R
import edu.iesam.marvelapp.core.cache.AppCache
import edu.iesam.marvelapp.core.serialization.JsonSerializer
import edu.iesam.marvelapp.features.superheroes.domain.SuperHero
import kotlin.jvm.java

/*
Conector para gestionar datos en un fivhero XML a traves de SharedPreferences

 */
class SuperHeroXmlLocalDataSource(
    private val context: Context,
    private val serializer: JsonSerializer<List<SuperHero>>,
    private val appCache: AppCache
) {

    private val keySuperHero: String = "superhero"
    private val ttl: Long = 5 * 60 * 1000

    //-- Me creo la fuente de datos xml: SharedPreferences.
    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.local_source_superhero), Context.MODE_PRIVATE
    )

    /**
     * Método para guardar un listado de SuperHero.
     */
    fun save(superHeroes: List<SuperHero>) {
        //apply, let, run  Scope Function
        sharedPref.edit().apply {
            putString(keySuperHero, serializer.toJson(superHeroes))
            apply()
        }
        appCache.updateTime(keySuperHero)
    }

    fun findAll(): List<SuperHero> {
        if (!appCache.isExpired(keySuperHero, ttl)) {
            val jsonList: String? = sharedPref.getString(keySuperHero, null)
            jsonList?.let {
                return serializer.fromJson(it)
            }
        }
        return emptyList()
    }

    fun findById(id: String): SuperHero? {
        return findAll().find { it.id == id }
    }

    fun deleteById(id: String) {
        save(findAll().filterNot { it.id == id })
    }

    fun deleteAll() {
        sharedPref.edit().apply {
            remove(keySuperHero)
            apply()
        }
    }
}

    /*public fun findById(id: String): SuperHero{

    }

    public fun save(superHeroes: List<SuperHero>){
       // sharedPref.edit().putString("id", superHeroes.first().id)
       // sharedPref.edit().putString("name", superHeroes.first().name)
       // sharedPref.edit().putString("slug", superHeroes.first().slug)
        superHeroes.forEach { superHero ->
            with(sharedPref.edit()){
            putString("${superHero.id}",gson.toJson(superHero))
            apply()

            }
        }


        //Dos formas de persistir en local
        //sharedPref.edit().apply() //Asincrono
        //sharedPref.edit().commit() // Sincrono
    }
    fun findById(id: String): SuperHero{
        val superHeroJson = sharedPref.getString(id,"")
        return gson.fromJson(superHeroJson, SuperHero::class.java)
    }*/
