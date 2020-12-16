package ar.edu.unahur.obj2.minions

abstract class Tarea() {

    var pendiente = true
    fun noPendiente() {pendiente = false}
    abstract var dificultad: Int
    abstract fun realizarLaTarea(minion: Minion)
    abstract fun puedeSerRealizada(minion: Minion):Boolean
}

class ArreglarMaquina(val herramientas: MutableList<String>, val complejidad: Int) : Tarea(){

    override var dificultad = complejidad * 2

    override fun puedeSerRealizada(minion: Minion): Boolean = puedeRepararMaquina(minion)

    fun puedeRepararMaquina(minion: Minion) = minion.estamina >= complejidad && tieneHerramientas(minion)

    fun tieneHerramientas(minion: Minion) = herramientas.all { it in minion.rol.herramientas}

    fun repararMaquina(minion: Minion) {
        minion.disminuirEstamina(complejidad)
        noPendiente()
    }

    override fun realizarLaTarea(minion: Minion) {
        if (puedeSerRealizada(minion)) repararMaquina(minion)
        else throw Exception("esta tarea no puede ser realizada...")
    }

}

class DefenderSector(val sector: Sector) : Tarea(){

    override var dificultad = sector.gradoDeAmenaza

    fun dificultadPorRaza(minion: Minion) : Int { if (1 == minion.ojos )
        return  dificultad * 2
    else return dificultad
    }

    override fun puedeSerRealizada(minion: Minion) =
        minion.fuerza() >= dificultadPorRaza(minion) && minion.rol != Limpiador

    override fun realizarLaTarea(minion: Minion) {
        if (this.puedeSerRealizada(minion)){
            minion.defender(sector)
            sector.sectorAtacado()
            noPendiente()
        } else{
            throw Exception ("No puede ser defendido")
        }
    }
}

object difucultadPorGremio { var dificultad = 10 }

class LimpiarSector(val sector: Sector) : Tarea(){

    override var dificultad = difucultadPorGremio.dificultad

    fun tamanioYEstamina(minion: Minion) =
        minion.estamina >= 4 && sector.esGrande || minion.estamina >= 1 && !sector.esGrande

    override fun puedeSerRealizada(minion: Minion) =
        minion.rol == Limpiador || tamanioYEstamina(minion)

    override fun realizarLaTarea(minion: Minion) {
        if ( puedeSerRealizada(minion) )  {
            sector.serLimpiado()
            minion.disminuirEstaminaSiNoEsLimpiador(dificultad)
            noPendiente()
        }
        else throw Exception (" No le da")
    }

}


