package ar.edu.unahur.obj2.minions

abstract class Tarea() {
    abstract fun puedeRealizarTarea(minion: Minion):Boolean

    abstract val dificultad: Int

    /*abstract fun puedeRepararMaquina(minion: Minion): Boolean*/

}

class ArreglarMaquina(val herramientas: MutableList<String>, val complejidad: Int) : Tarea(){

    override fun puedeRealizarTarea(minion: Minion): Boolean = puedeRepararMaquina(minion)

    override val dificultad = complejidad * 2

    fun puedeRepararMaquina(minion: Minion) = minion.estamina >= complejidad && tieneHerramientas(minion)
    fun tieneHerramientas(minion: Minion) = herramientas.all { it in minion.rol.herramientas}
    fun repararMaquina(minion: Minion) { if (puedeRepararMaquina(minion)) minion.estamina -= complejidad }


}
/*class LimpiarSector() : Tarea()*/


class DefenderSector(override val dificultad: Int) : Tarea(){

    override fun puedeRealizarTarea(minion: Minion) = true

}

