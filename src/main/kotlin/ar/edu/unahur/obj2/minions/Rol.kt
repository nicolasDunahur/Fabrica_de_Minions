package ar.edu.unahur.obj2.minions

abstract class Rol() {

    open var herramientas = mutableListOf<String>()
    var danioExtra = 0

    open fun fuerza(minion: Minion) = (minion.estamina/2) + 2
    open fun defender(minion: Minion) {}
}


object Soldado : Rol() {
    val arma = String()

    override fun fuerza(minion: Minion): Int {
        return super.fuerza(minion) + danioExtra
    }
    fun ganarExperiencia() { danioExtra += 2 }

    override fun defender(minion: Minion) {
        this.ganarExperiencia()
    }

}

object Obrero : Rol() {
    override var herramientas =  mutableListOf<String>("pala","serrucho","martillo","destornillador")
    override fun defender(minion: Minion) {
        this.perderlaMitadDeLaEstamina(minion)
    }
    fun perderlaMitadDeLaEstamina(minion: Minion) {
        minion.disminuirEstamina((minion.estamina / 2))
    }
}

object Limpiador : Rol() {
    override fun defender(minion: Minion) = throw Exception("Me niego")
}

/* hay que repensar quien conviene que recuerde o ejecute las cosas si hacer

    unaTarea.puedeSerRealizada(unMinion) o unMinion.puedeRealizar(unaTarea)

    en todas las tareas es necesario el dato de un minion como parametro
    en capataz se complica.
*/

// mismas caracteristicas que obrero
object Capataz: Rol(){
    val subAlternos = mutableListOf<Minion>()
    fun realizarTarea(unaTarea: Tarea){
        // unaTarea.realizarsePor(this.?seleccionarElMejor())

    }

    fun seleccionarElMejor() = subAlternos.maxBy{ it.experiencia() }
    fun empladosPuedenHacerla(tarea: Tarea) {
        subAlternos.any {  tarea.puedeSerRealizada(it)}
    }



}
