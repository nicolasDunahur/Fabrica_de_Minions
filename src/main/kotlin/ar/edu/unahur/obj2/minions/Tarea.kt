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
    }

}



class DefenderSector(val sector: Sector) : Tarea(){
    override var dificultad = sector.gradoDeAmenaza // incompleto

    override fun puedeSerRealizada(minion: Minion) =
            minion.fuerza()>= dificultad // &&  minion.rol <==> Limpiador()

    override fun realizarsePor(minion: Minion) {
        if (this.puedeSerRealizada(minion)) {
            minion.defender()
            sector.defendido()
        }
    }
}

class LimpiarSector(val sector: Sector) : Tarea(){
    override var dificultad = 10
    override fun puedeSerRealizada(minion: Minion) =
            this.mayorA4YGrande(minion) || this.esLimpiador()

    fun mayorA4YGrande(minion: Minion) =
            minion.estamina >= 4 && sector.esGrande

    fun esLimpiador(minion: Minion) =
            minion.rol == Limpiador



}
