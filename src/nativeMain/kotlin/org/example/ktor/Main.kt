package org.example.ktor

import io.ktor.client.*
import io.ktor.client.engine.curl.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) = runBlocking {

    val client = HttpClient(Curl) {
        args.firstOrNull()?.let { path ->
            println("setting caPath=$path")
            engine {
                caPath = path
            }
        }
    }
    
    val res = client.get("https://kotlinlang.org/")
    println("status=${res.status} bytes=${res.bodyAsBytes().size}")

    client.close()
}
