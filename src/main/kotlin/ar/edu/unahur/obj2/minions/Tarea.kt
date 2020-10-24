package ar.edu.unahur.obj2.minions

abstract class Tarea() {

    abstract var dificultad: Int
    abstract fun realizarLaTarea(minion: Minion)
    abstract fun puedeSerRealizada(minion: Minion):Boolean
}

class ArreglarMaquina(val herramientas: MutableList<String>, val complejidad: Int) : Tarea(){

    override var dificultad = complejidad *2

    override fun puedeSerRealizada(minion: Minion): Boolean = puedeRepararMaquina(minion)

    fun puedeRepararMaquina(minion: Minion) = minion.estamina >= complejidad && tieneHerramientas(minion)

    fun tieneHerramientas(minion: Minion) = herramientas.all { it in minion.rol.herramientas}

    fun repararMaquina(minion: Minion) { minion.estamina -= complejidad }

    override fun realizarLaTarea(minion: Minion) {
        if (puedeSerRealizada(minion)) repararMaquina(minion)
        else throw Exception("esta tarea no puede ser realizada...")
    }

}

class DefenderSector(val sector: Sector) : Tarea(){

    override var dificultad = sector.gradoDeAmenaza
    // diferente para ciclope y biclope

    override fun realizarLaTarea(minion: Minion) {
        if (this.puedeSerRealizada(minion)){
            minion.defender(sector)
            sector.sectorAtacado()
        } else{
            throw Exception ("No puede ser defendido")
        }
    }

    override fun puedeSerRealizada(minion: Minion) =
            minion.fuerza() >= dificultad  //&& minion.rol != Limpiador

}

object difucultadPorGremio { var dificultad = 10 }



class LimpiarSector(val sector: Sector) : Tarea(){

    override var dificultad = difucultadPorGremio.dificultad

    override fun puedeSerRealizada(minion: Minion) =
            this.tamanioYEstamina(minion)

    fun tamanioYEstamina(minion: Minion) =
            minion.estamina >= 4 && sector.esGrande || minion.estamina >= 1 && !sector.esGrande

    //  muchos if y analizar si el profe lo quiere asi. nombres feos de funciones. mucho codigo
    override fun realizarLaTarea(minion: Minion) {
        if (puedeSerRealizada(minion))  { sector.serLimpiado() }
        else { this.realizarseSiNoEsLimpiadorYPuede(minion) }
    }
    fun realizarseSiNoEsLimpiadorYPuede(minion: Minion) {
        if (this.puedeSerRealizada(minion)) {
            sector.serLimpiado()
            minion.disminuirEstamina(dificultad)
        }
        else {throw Exception ("No le dá")}
    }
}


