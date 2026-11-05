package edu.iesam.marvelapp.features.superheroes.data.local.xml

import android.content.Context
import edu.iesam.marvelapp.R
import edu.iesam.marvelapp.features.superheroes.domain.SuperHero
import kotlin.jvm.java

/*
Conector para gestionar datos en un fivhero XML a traves de SharedPreferences


 */
class SuperHeroXmlLocalDataSource(private val context: Context, private val serialization: AppSerialization) {

    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.local_source_superhero), Context.MODE_PRIVATE)



    public fun findAll(): List<SuperHero>{
        //sharedPref.getString()

        val superHero = SuperHero("1", "SuperWoman", "super-woman", "https//")
        val json = serialization.toJson(superHero)
        serialization.fromJson(json, SuperHero::class.java)


        return emptyList()
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
}