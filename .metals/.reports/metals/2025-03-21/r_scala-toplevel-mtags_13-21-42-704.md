error id: file://<WORKSPACE>/app/src/main/scala/org/HTTP/Client.scala:[312..315) in Input.VirtualFile("file://<WORKSPACE>/app/src/main/scala/org/HTTP/Client.scala", "package org.HTTP

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.util.ByteString

import scala.concurrent.Future
import scala.util.{ Failure, Success }

class Client(val uri: String){

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
        }
        
    }
}
")
file://<WORKSPACE>/file:<WORKSPACE>/app/src/main/scala/org/HTTP/Client.scala
file://<WORKSPACE>/app/src/main/scala/org/HTTP/Client.scala:16: error: expected identifier; obtained def
    def getrequest(): Future[String] = {
    ^
#### Short summary: 

expected identifier; obtained def