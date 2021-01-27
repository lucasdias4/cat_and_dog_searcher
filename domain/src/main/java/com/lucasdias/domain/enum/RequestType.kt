package com.lucasdias.domain.enum

enum class RequestType(val text: String) {
    CAT("Cat"),
    DOG("Dog"),
    BOTH("Both");

    companion object {
        fun getRequestTypeByText(option: String?): RequestType {
            return when (option) {
                CAT.text -> CAT
                DOG.text -> DOG
                BOTH.text -> BOTH
                else -> CAT
            }
        }
    }
}
