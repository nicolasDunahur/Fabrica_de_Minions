package ar.edu.unahur.obj2.minions

import kotlin.properties.Delegates

abstract class Rol() {

    open var herramientas = mutableListOf<String>()

    open fun IncrementarFuerza(minion: Minion)  = 0
    open fun defender(minion: Minion) {}
    open fun experienciaDeSubAlternos() = 0

}

open class Obrero() : Rol() {

    override var herramientas =  mutableListOf<String>("pala","serrucho","martillo","destornillador")

    override fun defender(minion: Minion) {
        this.perderlaMitadDeLaEstamina(minion)
    }
    fun perderlaMitadDeLaEstamina(minion: Minion) {
        minion.disminuirEstamina((minion.estamina / 2))
    }
}

class Capataz: Obrero() {
    lateinit var subAlternos : MutableList<Minion>

    override fun experienciaDeSubAlternos() = 40 //subAlternos.sumBy{ it.experiencia() }

    fun seleccionarElMejor() = subAlternos.maxBy{ it.experiencia() }

    fun empladosPuedenHacerla(tarea: Tarea) {
        subAlternos.any {  tarea.puedeSerRealizada(it)}
    }
    fun experienciaCapataz() = experienciaDeSubAlternos()

}
class Soldado : Rol() {

    var danioExtra = 0

    val arma = String()


    override fun IncrementarFuerza(minion: Minion) = danioExtra
    fun ganarExperiencia() { danioExtra += 2 }

    override fun defender(minion: Minion) {
        this.ganarExperiencia()
    }
}

object Limpiador : Rol() {

    override fun defender(minion: Minion) = throw Exception("Me niego")
}
