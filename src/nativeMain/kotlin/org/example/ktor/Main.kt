package org.example.ktor

import io.ktor.client.*
import io.ktor.client.engine.curl.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) = runBlocking {

    val client = HttpClient(Curl) {
        when (val path = args.getOrNull(1)) {
            null -> {}
            else -> engine {
                when (args.getOrNull(0)) {
                    "info" -> {
                        println("setting caInfo=$path")
                        caInfo = path
                    }
                    "path" -> {
                        println("setting caPath=$path")
                        caPath = path
                    }
                    else -> error("invalid first arg, must be 'info' or 'path'")
                }
            }
        }
    }

    val res = client.get("https://kotlinlang.org/")
    println("status=${res.status} bytes=${res.bodyAsBytes().size}")

    client.close()
}
