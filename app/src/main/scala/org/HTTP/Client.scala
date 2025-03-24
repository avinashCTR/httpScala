package org.HTTP

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.util.ByteString

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.util.{Failure, Success}

class Client() {

    // Initialize the ActorSystem only once
    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "SingleRequest")
    implicit val executionContext = system.executionContext

    def request(uri:String, request_type: String, data: Option[String] = None): String = {
        request_type match {
            case "GET"  => Await.result(getrequest(uri), Duration.Inf)
            case "POST" => Await.result(postrequest(uri,data.getOrElse("")), Duration.Inf)
            case _      => "Invalid request type"
        }
    }

    def postrequest(uri: String ,data: String): Future[String] = {
        val entity = HttpEntity(ContentTypes.`application/json`, data) // Set JSON body
        val request = HttpRequest(method = HttpMethods.POST, uri = uri, entity = entity)

        val responseFuture: Future[HttpResponse] = Http().singleRequest(request)

        responseFuture.flatMap { response =>
            response.entity.dataBytes.runFold(ByteString(""))(_ ++ _).map(_.utf8String)
        }.recover {
            case ex: Exception => s"Error: ${ex.getMessage}"
        }
    }

    def getrequest(uri:String): Future[String] = {
        val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = uri))

        responseFuture.flatMap { response =>
            response.entity.dataBytes.runFold(ByteString(""))(_ ++ _).map(_.utf8String)
        }.recover {
            case ex: Exception => s"Error: ${ex.getMessage}"
        }
    }
    
    // Call this explicitly after making all requests
    def shutdown(): Unit = {
        system.terminate()
    }
}
