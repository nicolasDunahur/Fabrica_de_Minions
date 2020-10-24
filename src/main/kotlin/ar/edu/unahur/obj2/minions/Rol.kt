package ar.edu.unahur.obj2.minions

import kotlin.properties.Delegates

abstract class Rol() {

    open var herramientas = mutableListOf<String>()
    abstract fun puedeRealizarTarea(tarea: Tarea):Boolean


    open fun fuerza(minion: Minion) = (minion.estamina/2) + 2
    open fun defender(minion: Minion) {}
}

open class Obrero() : Rol() {

    override var herramientas =  mutableListOf<String>("pala","serrucho","martillo","destornillador")

    // hay q pasar tota la logica de tarea a rol
    override fun puedeRealizarTarea(tarea: Tarea) = true



    override fun defender(minion: Minion) {
        this.perderlaMitadDeLaEstamina(minion)
    }
    fun perderlaMitadDeLaEstamina(minion: Minion) {
        minion.disminuirEstamina((minion.estamina / 2))
    }
}

class Capataz: Obrero() {
    lateinit var subAlternos : MutableList<Minion>

    // falta sumar la experiencia

    fun seleccionarElMejor() = subAlternos.maxBy{ it.experiencia() }

    fun empladosPuedenHacerla(tarea: Tarea) {
        subAlternos.any {  tarea.puedeSerRealizada(it)}
    }

}
class Soldado : Rol() {

    var danioExtra = 0

    val arma = String()

    // pasar toda la logica de tarea a rol. Decidir cual dejar
    override fun puedeRealizarTarea(tarea: Tarea) = true

    override fun fuerza(minion: Minion): Int {
        return super.fuerza(minion) + danioExtra
    }
    fun ganarExperiencia() { danioExtra += 2 }

    override fun defender(minion: Minion) {
        this.ganarExperiencia()
    }
}

class Limpiador : Rol() {

    override fun defender(minion: Minion) = throw Exception("Me niego")
    override fun puedeRealizarTarea(tarea: Tarea) = true
}
