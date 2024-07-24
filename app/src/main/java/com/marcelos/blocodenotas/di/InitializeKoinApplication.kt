package com.marcelos.blocodenotas.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.com_marcelos_blocodenotas_di_modules_LocalStorageModule as localStorageModule

class InitializeKoinApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                defaultModule,
                localStorageModule
            )
        }
    }
}