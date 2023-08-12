package com.indramahkota.android.exploration.di

import coil.ImageLoader
import coil.decode.SvgDecoder
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.includes
import org.koin.dsl.lazyModule

@OptIn(KoinExperimentalAPI::class)
val appModule = lazyModule {
  single<ImageLoader> {
    ImageLoader.Builder(androidContext())
      .components {
        add(SvgDecoder.Factory())
      }
      .build()
  }
}

@OptIn(KoinExperimentalAPI::class)
val allModules = lazyModule {
  includes(appModule)
}
