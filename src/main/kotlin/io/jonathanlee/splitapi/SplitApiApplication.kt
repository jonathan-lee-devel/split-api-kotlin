package io.jonathanlee.splitapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SplitApiApplication

fun main(args: Array<String>) {
	runApplication<SplitApiApplication>(*args)
}
