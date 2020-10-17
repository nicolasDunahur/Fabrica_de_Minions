package ar.edu.unahur.obj2.minions

import jdk.internal.joptsimple.internal.Strings

class Minion(var clase: Clase, val raza: Raza,var estamina: Float,var danio: Float) {
    fun estaContento() = estamina >= 30
    //fun defender(estamina: Float) =
}

abstract class Clase {
    //open fun defender() = (estamina/2) + 2
}

class Soldado : Clase() {
    val arma = String()
    //var danioMejorando = danio
    //override fun defender() {

    //    this.ganarPractica()
    //}

    //fun ganarPractica() = danioMejorando + 2
}

object Obrero : Clase() {
    var cinturon =  mutableListOf<Strings>()


}

object Limpiador : Clase() {
    //override fun defender(): Nothing = throw Exception("Me niego")

}

interface Raza

object Biclopes: Raza {
    val ojos = 2
    val punteria = 1
    fun estamina(estamina: Int) = estamina.plus(10)
}

object Ciclopes: Raza {
    val ojos = 1
    val punteria = 0.5
    var estamina = Float
}
