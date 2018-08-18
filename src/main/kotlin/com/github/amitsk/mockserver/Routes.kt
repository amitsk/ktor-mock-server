package com.github.amitsk.mockserver

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.afterburner.AfterburnerModule
import com.github.amitsk.mockserver.Payloads.countriesPayload
import com.github.amitsk.mockserver.Payloads.sunrisePayLoad
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.jackson.jackson
import io.ktor.locations.Location
import io.ktor.locations.Locations
import io.ktor.response.respondText
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.locations.*
import io.ktor.request.path
import org.slf4j.event.Level

@Location("/json")
class SunriseSunset

@Location("/countries")
class Countries


fun Application.mainModule() {
  install(CORS) {
    method(HttpMethod.Options)
    method(HttpMethod.Get)
    method(HttpMethod.Post)
    method(HttpMethod.Put)
    method(HttpMethod.Delete)
    method(HttpMethod.Patch)
    header(HttpHeaders.Authorization)
    allowCredentials = true
    anyHost()
  }
  install(DefaultHeaders)
  install(Compression)

  install(CallLogging) {
    level = Level.INFO
    filter { call -> call.request.path().startsWith("/json") }
    filter { call -> call.request.path().startsWith("/countries") }
  }


  install(ContentNegotiation) {
    jackson {
      registerModule(AfterburnerModule())
      registerModule(JavaTimeModule())
    }
  }

  install(Locations)

  routing {
    route("/") {
      get<SunriseSunset> { _ ->
        call.respondText(sunrisePayLoad, ContentType.Application.Json)
      }
      get<Countries> { _ ->
        call.respondText(countriesPayload, ContentType.Application.Json)
      }
    }
  }
}