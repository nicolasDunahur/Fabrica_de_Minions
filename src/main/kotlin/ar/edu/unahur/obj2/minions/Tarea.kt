package ar.edu.unahur.obj2.minions

abstract class Tarea(val dificultad: Int) {

    abstract fun puedeRealizarTarea(minion: Minion): Boolean

}

class ArreglarMaquina(herramientas: MutableList<String>, dificultad: Int) : Tarea(dificultad){

    override fun puedeRealizarTarea(minion: Minion) = minion.estamina >= dificultad

    fun tieneHerramientas(minion: Minion) = minion.rol


}
/*class LimpiarSector() : Tarea()
class DefenderSector() : Tarea()*/
