package ar.edu.unahur.obj2.minions

abstract class Rol() {

    open var herramientas = mutableListOf<String>()
    var danioExtra = 0

    open fun fuerza(minion: Minion) = (minion.estamina/2) + 2
    fun puedeDefender(unSector: Sector, unMinion: Minion) = unSector.gradoDeAmenaza <= this.fuerza(unMinion)
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
