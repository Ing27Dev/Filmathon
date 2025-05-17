package com.ingdev.filmathon.network

import com.ingdev.filmathon.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.ingdev.filmathon.data.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

//Interfaz para definir los endpoints disponibles de la API TMDB.
interface ApiService {

    //Busca peliculas usando filtros como genero, año o popularidad
    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("api_key") apiKey: String,
        @Query("with_genres") genreId: String?,
        @Query("primary_release_year") year: Int?,
        @Query("language") language: String = "es-ES",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1
    ): MovieResponse

    //Busaca peliculas por palabras clave en el titulo o en la sinopsis
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") keyword: String,
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int = 1
    ): MovieResponse
}

//Singelton que proporciona una instancia de Retrofit configurada
object RetrofitInstance {
    //configuración base de Retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        }

    //Punto de acceso a la implementación de la API
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}