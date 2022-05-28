package Objetos
import Actors.Receptor
import akka.actor.Props

object Receptor {
  def props(message: Int): Props = Props(new Receptor(message))
  final case class TextCompleto(message: String)
  final case class TrabajoTerminado(row: Int, work: Array[Int])
}