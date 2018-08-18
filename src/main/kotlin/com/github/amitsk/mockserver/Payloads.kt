package com.github.amitsk.mockserver

object Payloads {
  val sunrisePayLoad = "{\n" +
      "      \"results\": {\n" +
      "          \"sunrise\": \"5:02:46 AM\",\n" +
      "          \"sunset\": \"7:40:32 PM\",\n" +
      "          \"solar_noon\": \"12:21:39 PM\",\n" +
      "          \"day_length\": \"14:37:46\",\n" +
      "          \"civil_twilight_begin\": \"4:32:19 AM\",\n" +
      "          \"civil_twilight_end\": \"8:10:59 PM\",\n" +
      "          \"nautical_twilight_begin\": \"3:54:22 AM\",\n" +
      "          \"nautical_twilight_end\": \"8:48:56 PM\",\n" +
      "          \"astronomical_twilight_begin\": \"3:11:57 AM\",\n" +
      "          \"astronomical_twilight_end\": \"9:31:21 PM\"\n" +
      "      },\n" +
      "      \"status\": \"OK\"\n" +
      "  }"

  val countriesPayload = Payloads::class.java.getResource("/data/countries.json").readText()
}
