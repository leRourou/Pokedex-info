package com.example.pokemon

import android.app.Application
import com.example.pokemon.data.AppContainer
import com.example.pokemon.data.DefaultAppContainer

class PokedexApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
