package com.github.amitsk.mockserver

import com.typesafe.config.ConfigFactory
import io.ktor.config.HoconApplicationConfig
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.LoggerFactory


fun main(args: Array<String>) {

  embeddedServer(Netty, applicationEngineEnvironment {
    log = LoggerFactory.getLogger("ktor.application")
    config = HoconApplicationConfig(ConfigFactory.load()) // Provide a Hocon config file
    val configPort: String = config.propertyOrNull("ktor.deployment.port")?.getString() ?: "8080"

    module {
      mainModule()
    }

    connector {
      port = configPort.toInt()
    }
  }).start(true)
}

