package com.example.domain.custom_exceptions

class ConnectionError(message: String = "No internet connection")
    : Throwable(message)