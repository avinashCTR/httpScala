error id: file://<WORKSPACE>/app/src/main/scala/org/HTTP/Client.scala:[250..253) in Input.VirtualFile("file://<WORKSPACE>/app/src/main/scala/org/HTTP/Client.scala", "package org.HTTP

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._

import scala.concurrent.Future
import scala.util.{ Failure, Success }

class 
    
    def request(): String = {
        implicit val system = ActorSystem(Behaviors.empty, "SingleRequest")
        implicit val executionContext = system.executionContext

        val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = uri))

        responseFuture
            .onComplete {
                case Success(res) => {
                    println(res)
                    res.discardEntityBytes()
                }
                case Failure(_) => sys.error("something wrong")
            }
        "ok"
    }

}")
file://<WORKSPACE>/file:<WORKSPACE>/app/src/main/scala/org/HTTP/Client.scala
file://<WORKSPACE>/app/src/main/scala/org/HTTP/Client.scala:13: error: expected identifier; obtained def
    def request(): String = {
    ^
#### Short summary: 

expected identifier; obtained def