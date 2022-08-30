package com.kh69.passmath.common.domain.model

import java.io.IOException


class NoMoreQuestionsException(message: String) : Exception(message)

class NetworkUnavailableException(message: String = "No network available :(") :
    IOException(message)

class NetworkException(message: String) : Exception(message)