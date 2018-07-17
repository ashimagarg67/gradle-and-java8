package com.cmartin

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors


fun main(args: Array<String>) {
    val command = "yo --version"
    val arguments = arrayOf("")
    println("Bash invoker!")
    val process = Runtime.getRuntime().exec(command, arguments)
    process.waitFor(5000, TimeUnit.MILLISECONDS)

    println("isAlive: ${process.isAlive}")
    println("exitValue: ${process.exitValue()}")

    val result = BufferedReader(InputStreamReader(process.inputStream))
            .lines().collect(Collectors.joining("\n"))
    if (result.isNotBlank()) println("result: $result")

    val error = BufferedReader(InputStreamReader(process.errorStream))
            .lines().collect(Collectors.joining("\n"))
    if (error.isNotBlank()) println("error: $error")

}
