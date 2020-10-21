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
    override var dificultad = sector.gradoDeAmenaza // incompleto, no se como encararlo

    override fun puedeSerRealizada(minion: Minion) =
            minion.fuerza()>= dificultad // &&  minion.rol <==> Limpiador() como hacer el diferente?

    override fun realizarsePor(minion: Minion) {
        if (this.puedeSerRealizada(minion)) {
            minion.defender()
            sector.defendido()
        }
    }
}

object difucultadPorGremio { var dificultad = 10 }

class LimpiarSector(val sector: Sector) : Tarea(){
    override var dificultad = difucultadPorGremio.dificultad

    override fun puedeSerRealizada(minion: Minion) =
            this.tamanioYEstamina(minion) || this.esLimpiador(minion)

    fun tamanioYEstamina(minion: Minion) =
            minion.estamina >= 4 && sector.esGrande
                    || minion.estamina >= 1 && sector.esGrande == false

    fun esLimpiador(minion: Minion) =
            minion.rol == Limpiador

    //  muchos if y analizar si el profe lo quiere asi. nombres feos de funciones. mucho codigo
    override fun realizarsePor(minion: Minion) {
        if (this.esLimpiador(minion)) {
            sector.serLimpiado()
        } else {
            this.realizarseiNoEsLimpiadorYPuede(minion)
        }
    }
    fun realizarseiNoEsLimpiadorYPuede(minion: Minion) {
        if (this.puedeSerRealizada(minion)) {
            sector.serLimpiado()
            minion.disminuirEstamina(dificultad)
        }else {
            throw Exception ("No le dá")
        }
    }
}
