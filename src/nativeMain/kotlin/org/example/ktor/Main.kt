package org.example.ktor

import io.ktor.client.*
import io.ktor.client.engine.curl.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) = runBlocking {

    val argsMap: Map<String, String> = args.associate { it.split("=").run { first() to last() } }

    val client = HttpClient(Curl) {
        engine {
            argsMap["caInfo"]?.let { path ->
                println("setting caInfo=$path")
                caInfo = path
            }
            argsMap["caPath"]?.let { path ->
                println("setting caPath=$path")
                caPath = path
            }
        }
    }

    try {
        val res = client.get("https://kotlinlang.org/")
        println("status=${res.status} bytes=${res.bodyAsBytes().size}")
    } finally {
        client.close()
    }
}
