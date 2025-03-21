package org

import org.HTTP.Client

object Main {
  def main(args: Array[String]): Unit = {
    
    // Testing online dummy API
    val client = new Client()

    var response = client.request("https://echo.free.beeceptor.com","GET")

    println(response)

    response = client.request("https://echo.free.beeceptor.com","POST", Some("""{"name": "John Doe"}"""))

    //printing the type of response
    println(response)

    client.shutdown()

  }
}