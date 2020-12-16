import Fruta.Fruta
import Rol.Rol

ackage ar.edu.unahur.obj2.minions

import kotlin.math.min

abstract class Minion(var rol: Rol, var estamina: Int) {
    abstract val ojos: Int

    var tareaRealizadas = mutableListOf<Tarea>()

    open fun comerFruta(fruta: Fruta) {
        estamina += fruta.energia
    }

    fun defender(unSector:Sector) { rol.defender(this) }

    fun disminuirEstamina(cuanto: Int) { estamina -= cuanto}

    fun disminuirEstaminaSiNoEsLimpiador(cuanto: Int) {
        if (rol == Limpiador) {
            this.disminuirEstamina(cuanto)
        }
    }

    open fun experiencia() = (tareaRealizadas.size * tareaRealizadas.sumBy { it -> it.dificultad } )+ rol.experienciaDeSubAlternos()

    fun puedeRealizarTarea(tarea: Tarea) = tarea.puedeSerRealizada(this)
    fun realizarTarea(tarea: Tarea) = tarea.realizarLaTarea(this)

    open fun fuerza() = (estamina / 2) + 2 + rol.IncrementarFuerza(this)

    fun estaContento() = estamina >= 9

    fun agregarTarea(tarea: Tarea) = tareaRealizadas.add(tarea)

}

class Biclope(rol: Rol,estamina: Int) : Minion(rol, estamina) {

    override val ojos: Int = 2

    override fun comerFruta(fruta: Fruta){
        estamina = min(10, fruta.energia + estamina)
    }


}

class Ciclople(rol: Rol, estamina: Int) : Minion(rol, estamina) {
    override val ojos: Int = 1

    override fun fuerza() = super.fuerza() /2



}
