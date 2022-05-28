import Objetos.{Receptor, Emisor}
import akka.actor.{ActorRef, ActorSystem}
import scala.io.StdIn
import scala.util.Random

object Ejecutor extends App {
  import Objetos.Emisor._
  import scala.collection.mutable.Map

  def generarMatriz(dim: Int): Array[Array[Int]] = {
    val rand = Random
    val matrix = Array.ofDim[Int](dim, dim)
    for(i <- 0 until dim) {
      for(j <- 0 until dim) {
        matrix(i)(j) = rand.nextInt(20)
      }
    }
    matrix
  }

  def imprimirMatriz(matrix: Array[Array[Int]]): Unit = {
    for {
      i <- 0 until matrix.length
      j <- 0 until matrix.length
    } println(s"($i)($j) = ${matrix(i)(j)}")
  }
  // sDimensiones de la matriz
  val dim = 10
  val a = generarMatriz(dim)
  val b = generarMatriz(dim)
  // Asisgnar en memoria la dimension
  val c = Array.ofDim[Int](dim, dim)
  // Contenedor para alojar todos los actores
  val system: ActorSystem = ActorSystem("Ejecutor")

  try {
    val numOfActors = a.length
    var actorRefs = Map[Int, ActorRef]()
    // Actor Receptor recibe todos los mensajes que mandan los acotres Emisores
    val listener: ActorRef = system.actorOf(Receptor.props(a.length), "ActorReceptor")
    // Generar los actores Emisores
    for(row <- 0 until numOfActors) {
      // map row key -> Hacer referencia a un Emisor especifico
      actorRefs += (row -> system.actorOf(Emisor.props(row, listener), s"Emisor-$row"))
      // Distribuir la carga de trabajo y mandar alerta de inicio
      actorRefs(row) ! QueMultiplicar(a(row), b)
      actorRefs(row) ! EjecutarMlt
    }
    println("Finalizar programa con varios Enter porfavor")
    StdIn.readLine()
  } finally {
    system.terminate()
  }
}