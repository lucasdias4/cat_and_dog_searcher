package com.lucasdias.base.di

import com.lucasdias.core.scheduler.RequestSchedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val baseModule = module {

    factory {
        getRequestSchedulers()
    }
}

private fun getRequestSchedulers(): RequestSchedulers {
    return RequestSchedulers(Schedulers.io(), AndroidSchedulers.mainThread())
}
