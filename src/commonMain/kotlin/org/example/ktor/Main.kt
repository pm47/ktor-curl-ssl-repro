package org.example.ktor

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {

    val client = HttpClient {

    }
    val res = client.get("https://kotlinlang.org/")
    println("status=${res.status} bytes=${res.bodyAsBytes().size}")

    client.close()
}
