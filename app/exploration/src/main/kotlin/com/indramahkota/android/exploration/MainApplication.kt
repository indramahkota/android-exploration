package com.indramahkota.android.exploration

import android.app.Application
import com.indramahkota.android.exploration.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.lazyModules

class MainApplication : Application() {

  @OptIn(KoinExperimentalAPI::class)
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@MainApplication)
      lazyModules(allModules)
    }
  }
}
