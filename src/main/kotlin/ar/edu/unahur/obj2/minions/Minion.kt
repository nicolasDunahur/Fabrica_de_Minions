package ar.edu.unahur.obj2.minions

import kotlin.math.min

abstract class Minion(var rol: Rol,var estamina: Int=0) {

    var tareaRealizadas = mutableListOf<Tarea>()

    open fun comerFruta(fruta:Fruta) {
        estamina += fruta.energia
    }
    fun defender(unSector:Sector) {
        rol.defender(unSector, this)
    }


    open fun fuerza() = rol.fuerza(this)
    fun experiencia() = tareaRealizadas.size * tareaRealizadas.sumBy { it -> it.dificultad }

    fun puedeRealizarTarea(tarea: Tarea) = tarea.puedeRealizarTarea(this)

}

class Biclope(rol: Rol, estamina: Int) : Minion(rol, estamina) {
    val ojos: Int = 2

    override fun comerFruta(fruta: Fruta){
        estamina = min(10, fruta.energia + estamina)
    }


}

class Ciclople(rol: Rol, estamina: Int) : Minion(rol, estamina) {
    val ojos: Int = 1

    override fun comerFruta(fruta: Fruta){
        estamina += fruta.energia
    }

    override fun fuerza(): Int {
        return super.fuerza()/2
    }

}



