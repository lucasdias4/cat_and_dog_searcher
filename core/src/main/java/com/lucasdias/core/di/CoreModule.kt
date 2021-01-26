package com.lucasdias.core.di

import com.lucasdias.core.connectivity.Connectivity
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val CONNECTIVITY = "CONNECTIVITY"

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val coreModule = module {

    single(named(CONNECTIVITY)) {
        Connectivity(androidApplication())
    }
}
