package com.kh69.passmath.data.api

object ApiConstants {
    const val BASE_ENDPOINT = "http://192.168.43.134:5000/api/v1/"
    const val AUTH_ENDPOINT = "oauth2/token/"
    const val QUESTIONS_ENDPOINT = "questions"

    const val KEY = "INSERT_YOUR_KEY_HERE"
    const val SECRET = "INSERT_YOUR_SECRET_HERE"
//    fun aK(): String { //API KEY
//        return SECRET.substring(35).reversed() + GO.f1 + "5687" + GO.f2 + "3657"+
//                GO.f2 + SECRET.substring(7,10) + GO.f1 + " 4fj6" + GO.f3 +
//                SECRET.substring(1..4) + GO.f3
//    }
}

object ApiParameters {
    const val TOKEN_TYPE = "Bearer "
    const val AUTH_HEADER = "Authorization"
    const val GRANT_TYPE_KEY = "grant_type"
    const val GRANT_TYPE_VALUE = "client_credentials"
    const val CLIENT_ID = "client_id"
    const val CLIENT_SECRET = "client_secret"

    const val PAGE = "page"
    const val LIMIT = "limit"
    const val YEAR = "year"
    const val TOPIC = "topic"
    const val SECTION = "section"
    const val PAPER = "paper"
    const val LOCATION = "location"
    const val DISTANCE = "distance"
    const val NAME = "name"
    const val AGE = "age"
    const val TYPE = "type"
}
