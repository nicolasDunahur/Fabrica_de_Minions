package ar.edu.unahur.obj2.minions

abstract class Tarea() {
    abstract var dificultad: Int
    abstract fun puedeSerRealizada(minion: Minion):Boolean
    abstract fun realizarsePor(minion: Minion)


}

class ArreglarMaquina(val herramientas: MutableList<String>, val complejidad: Int) : Tarea(){
    override var dificultad = complejidad * 2

    override fun puedeSerRealizada(minion: Minion): Boolean =
            this.estaminaMayorAComplejidad(minion) && this.tieneHerramientas(minion)

    fun estaminaMayorAComplejidad(minion: Minion) =
            minion.estamina >= complejidad

    fun tieneHerramientas(minion: Minion) =
            herramientas.all { it in minion.rol.herramientas}

    override fun realizarsePor(minion: Minion) {
        if (this.puedeSerRealizada(minion))
                minion.disminuirEstamina(complejidad)
        // preguntar si puede tener estamina negativa
    }

}



class DefenderSector(var amenaza: Int, val esGrande: Boolean, var estaLimpio: Boolean) : Tarea(){
    override var dificultad = amenaza // incompleto

    override fun puedeSerRealizada(minion: Minion) =
            minion.fuerza()>= dificultad // &&  minion.rol <==> Limpiador()

    override fun realizarsePor(minion: Minion) {
        if (this.puedeSerRealizada(minion)) {
            minion.defender()
            var amenaza = 0
            var estaLimpio = false
        }
    }





}

// class LimpiarSector() : Tarea()
