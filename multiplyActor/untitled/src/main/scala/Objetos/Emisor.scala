package Objetos
import Actors.Emisor
import akka.actor.{ActorRef, Props}

object Emisor {
  def props(message: Int, emisor: ActorRef)
  : Props = Props(new Emisor(message, emisor))
  final case class QueMultiplicar(matriz1: Array[Int], matriz2: Array[Array[Int]])
  case object EjecutarMlt
}