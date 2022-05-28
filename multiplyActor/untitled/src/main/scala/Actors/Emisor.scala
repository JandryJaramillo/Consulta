package Actors
import akka.actor.{ Actor, ActorRef}

class Emisor(message: Int, listenerActor: ActorRef) extends Actor {
  import Objetos.Emisor._
  import Objetos.Receptor._
  var emisorID = message
  var a = Array[Int]()
  var b = Array[Array[Int]]()
  var result = Array[Int]()

  def receive = {
    case QueMultiplicar(matrixA, matrixB) =>
      a = matrixA
      b = matrixB
      result = Array.ofDim[Int](a.length)

    case EjecutarMlt =>
      for (i <- 0 until a.length) {
        var temp = 0
        for (j <- 0 until b.length) {

          temp += a(j) * b(j)(i)
        }
        result(i) = temp
      }
      listenerActor ! TextCompleto(s"Emisor #$emisorID terminó")
      listenerActor ! TrabajoTerminado(message, result)
  }
}