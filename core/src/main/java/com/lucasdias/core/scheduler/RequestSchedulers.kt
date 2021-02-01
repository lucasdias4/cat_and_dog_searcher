package com.lucasdias.core.scheduler

import io.reactivex.rxjava3.core.Scheduler

data class RequestSchedulers(val subscribe: Scheduler, val observe: Scheduler)
