package com.example.ignite.models.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}