package org

import org.HTTP.Client

// object Main {
//   def main(args: Array[String]): Unit = {
    
//     // Testing online dummy API
//     val client = new Client()

//     var response = client.request("https://echo.free.beeceptor.com","GET")

//     println(response)

//     response = client.request("https://echo.free.beeceptor.com","POST", Some("""{"name": "John Doe"}"""))

//     //printing the type of response
//     println(response)

//     client.shutdown()

//   }
// }

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Main {
  def main(args: Array[String]): Unit = {
    val client = new Client()

    val startTime = System.nanoTime() // Start timer

    val futures = (1 to 1000).map { i =>
      Future {
        val requestStart = System.nanoTime() // Time for this request
        println(s"Sending request $i...")

        val response = client.request("https://echo.free.beeceptor.com", "POST", Some(s"""{"name": "John Doe $i"}"""))

        val requestEnd = System.nanoTime()
        val requestTimeMs = (requestEnd - requestStart) / 1e6 // Convert to milliseconds

        println(s"Response $i received in $requestTimeMs ms:\n$response")
      }
    }

    // Wait for all futures to complete
    Future.sequence(futures).onComplete { _ =>
      val endTime = System.nanoTime()
      val totalTimeMs = (endTime - startTime) / 1e6 // Convert to milliseconds
      println(s"\nTotal execution time: $totalTimeMs ms")

      client.shutdown()
    }
  }
}
