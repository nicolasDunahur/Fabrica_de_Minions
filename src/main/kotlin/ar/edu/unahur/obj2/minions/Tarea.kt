package ar.edu.unahur.obj2.minions

abstract class Tarea() {

    abstract val dificultad: Int

    abstract fun tareaPuedeSerRealizada(minion: Minion): Boolean

}

class ArreglarMaquina(val herramientas: MutableList<String>, val complejidad: Int) : Tarea(){
    override val dificultad = complejidad * 2

    override fun tareaPuedeSerRealizada(minion: Minion) = minion.estamina >= complejidad && tieneHerramientas(minion)
    fun tieneHerramientas(minion: Minion) = herramientas.all { it in minion.rol.herramientas}
    fun repararMaquina(minion: Minion) { if (tareaPuedeSerRealizada(minion)) minion.estamina -= complejidad }


}
/*class LimpiarSector() : Tarea()*/


class DefenderSector(override val dificultad: Int) : Tarea(){
    override fun tareaPuedeSerRealizada(minion: Minion) = true

}

