package Actors
import akka.actor.{ Actor}
import scala.concurrent.duration._

class Receptor(message: Int) extends Actor {
  import Objetos.Receptor._

  val startTime: Long = System.currentTimeMillis
  var resultMatrix = Array.ofDim[Array[Int]](message)
  var numOfRows = 0

  def receive = {
    //case DoneMsg(message) => println(message)
    case TrabajoTerminado(row, work) =>
      resultMatrix(row) = work
      numOfRows += 1
      if(numOfRows == resultMatrix.length) {
        println("Todos los Emisores terminaron. Calculation time: %s"
          .format((System.currentTimeMillis - startTime).millis))
                for {
                  i <- 0 until resultMatrix.length
                  j <- 0 until resultMatrix.length
                } {
                  println(s"($i)($j) = { ${resultMatrix(i)(j)} }")
                }
      }
  }
}
