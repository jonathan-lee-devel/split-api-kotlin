package io.jonathanlee.splitapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Main entry point for the application.
 *
 * @author Jonathan Lee <jonathan.lee.devel@gmail.com>
 */
@SpringBootApplication
class SplitApiApplication

fun main(args: Array<String>) {
	runApplication<SplitApiApplication>(*args)
}
