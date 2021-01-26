package com.lucasdias.ui_components.input.configuration

import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.view.inputmethod.EditorInfo.IME_ACTION_GO
import android.view.inputmethod.EditorInfo.IME_ACTION_NEXT
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.view.inputmethod.EditorInfo.IME_ACTION_SEND

enum class TextInputImeType(val id: Int, val action: Int) {
    DONE(id = 0, action = IME_ACTION_DONE),
    GO(id = 1, action = IME_ACTION_GO),
    NEXT(id = 2, action = IME_ACTION_NEXT),
    SEARCH(id = 3, action = IME_ACTION_SEARCH),
    SEND(id = 4, action = IME_ACTION_SEND);

    companion object {
        fun default() = DONE

        fun getImeType(id: Int): TextInputImeType {
            IME_ACTION_NEXT
            return when (id) {
                DONE.id -> DONE
                GO.id -> GO
                NEXT.id -> NEXT
                SEARCH.id -> SEARCH
                SEND.id -> SEND
                else -> throw IllegalArgumentException("Could not map this type of input text IME. ID not mapped: $id")
            }
        }
    }
}
