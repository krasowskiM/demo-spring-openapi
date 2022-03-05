package com.example.demospringopenapi.runner

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.example.demospringopenapi.ws"])
class SpringOpenapiApplication

fun main(args: Array<String>) {
    runApplication<SpringOpenapiApplication>(*args)
}