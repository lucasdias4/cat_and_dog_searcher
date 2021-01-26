package com.lucasdias.extensions

import android.content.Intent
import java.lang.Exception

fun Intent.getIntOrThrow(key: String): Int {
    return if (hasExtra(key)) {
        getIntExtra(key, 0)
    } else {
        throw Exception("Invalid intent key: $key")
    }
}
