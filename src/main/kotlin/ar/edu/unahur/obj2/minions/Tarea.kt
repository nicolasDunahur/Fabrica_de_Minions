package ar.edu.unahur.obj2.minions

abstract class Tarea() {


    abstract val dificultad: Int
    abstract fun realizarsePor(minion: Minion)
    abstract fun puedeSerRealizada(minion: Minion): Boolean

    fun esLimpiador(minion: Minion) =
            minion.rol == Limpiador

}

class ArreglarMaquina(val herramientas: MutableList<String>, val complejidad: Int) : Tarea() {
    override val dificultad = complejidad * 2
    fun tieneHerramientas(minion: Minion) = herramientas.all { it in minion.rol.herramientas }

    // fun puedeRepararMaquina(minion: Minion) = minion.estamina >= complejidad && tieneHerramientas(minion)
    override fun puedeSerRealizada(minion: Minion) = minion.estamina >= complejidad && tieneHerramientas(minion)

    override fun realizarsePor(minion: Minion) {
        if (this.puedeSerRealizada(minion))
            minion.disminuirEstamina(complejidad)
    }



}

class DefenderSector(val sector: Sector) : Tarea(){

    override var dificultad = sector.gradoDeAmenaza // incompleto, no se como encararlo

    // fun puededefender(minion: Minion) = minion.fuerza() >= sector.gradoDeAmenaza -- para mi esta demas
    override fun realizarsePor(minion: Minion) {
        if (this.puedeSerRealizada(minion)){
            //estos son los efectos que produce su ejecucion
            minion.defender(sector)
            sector.defendido()
        } else{
            throw Exception ("No puede ser defendido")
        }
    }

    override fun puedeSerRealizada(minion: Minion) =
            minion.fuerza() >= dificultad  &&  !this.esLimpiador(minion)

}

object difucultadPorGremio { var dificultad = 10 }



class LimpiarSector(val sector: Sector) : Tarea(){

    override var dificultad = difucultadPorGremio.dificultad

    override fun puedeSerRealizada(minion: Minion) =
            this.tamanioYEstamina(minion) || this.esLimpiador(minion)

    fun tamanioYEstamina(minion: Minion) =
            minion.estamina >= 4 && sector.esGrande || minion.estamina >= 1 && !sector.esGrande

    //  muchos if y analizar si el profe lo quiere asi. nombres feos de funciones. mucho codigo
    override fun realizarsePor(minion: Minion) {
        if (this.esLimpiador(minion))  {sector.serLimpiado()}
        else {this.realizarseSiNoEsLimpiadorYPuede(minion)}
    }
    fun realizarseSiNoEsLimpiadorYPuede(minion: Minion) {
        if (this.puedeSerRealizada(minion)) {
            sector.serLimpiado()
            minion.disminuirEstamina(dificultad)
        }
        else {throw Exception ("No le dá")}
    }
}


