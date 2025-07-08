package com.webmafia.webmafia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import io.github.cdimascio.dotenv.Dotenv
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class WebmafiaApplication

fun main(args: Array<String>) {
    val dotenv = Dotenv.load()
    System.out.println("SECRET KEY: " + dotenv.get("LOCAL_JWT_SECRET_KEY"));
    System.out.println("DataBase Url: " + dotenv.get("DB_URL"));
    runApplication<WebmafiaApplication>(*args)

    //runApplication<WebmafiaApplication>(*args)
}