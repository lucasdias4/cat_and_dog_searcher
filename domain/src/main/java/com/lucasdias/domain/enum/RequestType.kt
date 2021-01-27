package com.lucasdias.domain.enum

enum class RequestType(val text: String) {
    CAT("Search for cats"),
    DOG("Search for dogs"),
    BOTH("Search for both");

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
