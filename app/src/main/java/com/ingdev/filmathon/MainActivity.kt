package com.ingdev.filmathon

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.util.Log
import com.ingdev.filmathon.utils.Constants
import com.ingdev.filmathon.network.RetrofitInstance


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.discoverMovies(
                    apiKey = Constants.TMDB_API_KEY,
                    genreId = "28", // Acción
                    year = 1993, // Año de ejemplo
                    page = 1
                )

                response.results.forEach { movie ->
                    Log.d("API_TEST", "Película: ${movie.title}")
                }

            } catch (e: Exception) {
                Log.e("API_TEST", "Error al llamar a la API", e)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}