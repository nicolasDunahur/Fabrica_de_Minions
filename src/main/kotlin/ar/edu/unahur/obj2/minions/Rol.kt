package ar.edu.unahur.obj2.minions

abstract class Rol() {

    open var herramientas = mutableListOf<String>()

    open fun experienciaDeSubAlternos() = 0
    open fun IncrementarFuerza(minion: Minion) = 0
    open fun defender(minion: Minion) {}


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

    fun seleccionarElMejor() = subAlternos.maxBy{ it.experiencia() }

    fun empladosPuedenHacerla(tarea: Tarea) {
        subAlternos.any {  tarea.puedeSerRealizada(it)}
    }

    override fun experienciaDeSubAlternos() = subAlternos.sumBy { it.experiencia() }

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
