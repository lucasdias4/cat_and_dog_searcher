package com.lucasdias.base.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val baseModule = module {

    factory {
        getCoroutinesDispatchersIo()
    }
}

private fun getCoroutinesDispatchersIo() = Dispatchers.IO
