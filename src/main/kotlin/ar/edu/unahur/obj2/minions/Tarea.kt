package ar.edu.unahur.obj2.minions

abstract class Tarea() {
    abstract var dificultad: Int
    abstract fun puedeSerRealizada(minion: Minion):Boolean
    abstract fun realizarsePor(minion: Minion)


}

class ArreglarMaquina(val herramientas: MutableList<String>, val maquina: Maquina) : Tarea(){
    override var dificultad = maquina.complejidad * 2

    override fun puedeSerRealizada(minion: Minion): Boolean =
            this.estaminaMayorAComplejidad(minion) && this.tieneHerramientas(minion)

    fun estaminaMayorAComplejidad(minion: Minion) =
            minion.estamina >= maquina.complejidad

    fun tieneHerramientas(minion: Minion) =
            herramientas.all { it in minion.rol.herramientas}



    override fun realizarsePor(minion: Minion) {
        if (this.puedeSerRealizada(minion)) {
                minion.estamina -= maquina.complejidad }
        else {}
        // preguntar si puede tener estamina negativa
    }

}


/*
class DefenderSector(override val dificultad: Int) : Tarea(){

    override fun puedeSerRealizada(minion: Minion) = true

}

// class LimpiarSector() : Tarea()
*/