error id: file://<WORKSPACE>/app/src/main/scala/org/HTTP/Client.scala:[696..699) in Input.VirtualFile("file://<WORKSPACE>/app/src/main/scala/org/HTTP/Client.scala", "package org.HTTP

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.util.ByteString

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import scala.concurrent.Await
import scala.concurrent.duration.Duration

class Client(val uri: String){

    def request(request_type: String): String = {
        request_type match {
            case "GET" => {
                val response = Await.result(getrequest(), Duration.Inf)
                response
            }
            case _ => {
                "Invalid request type"
            }
        }
    }

    def 
    
    def getrequest(): Future[String] = {
        
        implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
        // needed for the future flatMap/onComplete in the end
        implicit val executionContext = system.executionContext

        val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = uri))

        responseFuture.flatMap { response =>
            response.entity.dataBytes.runFold(ByteString(""))(_ ++ _).map(_.utf8String)
        }.recover {
            case ex: Exception =>
                s"Error: ${ex.getMessage}"
        }.andThen {
            case _ => system.terminate()
        }
        
    }
}
")
file://<WORKSPACE>/file:<WORKSPACE>/app/src/main/scala/org/HTTP/Client.scala
file://<WORKSPACE>/app/src/main/scala/org/HTTP/Client.scala:30: error: expected identifier; obtained def
    def getrequest(): Future[String] = {
    ^
#### Short summary: 

expected identifier; obtained def