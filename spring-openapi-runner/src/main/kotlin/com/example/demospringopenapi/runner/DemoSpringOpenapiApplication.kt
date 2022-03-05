package com.example.demospringopenapi.runner

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication(scanBasePackages = ["com.example.demospringopenapi"])
class DemoSpringOpenapiApplication

fun main(args: Array<String>) {
    runApplication<DemoSpringOpenapiApplication>(*args)
}