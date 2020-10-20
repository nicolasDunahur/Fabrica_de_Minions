package ar.edu.unahur.obj2.minions

abstract class Rol {
    open fun fuerza(minion: Minion) = (minion.estamina/2) + 2
    open fun puedeDefender(unSector: Sector, unMinion: Minion) = unSector.gradoDeAmenaza <= this.fuerza(unMinion)
    abstract fun defender(unSector: Sector, minion: Minion)
}


object Soldado : Rol() {
    val arma = String()
    var danioExtra = 0

    fun ganarExperiencia() { danioExtra += 2 }

    override fun fuerza(minion: Minion): Int {
        return super.fuerza(minion) + danioExtra
    }

    override fun defender(unSector: Sector, minion: Minion) {
        this.ganarExperiencia()
    }
}

object Obrero : Rol() {
    var herramientas =  mutableListOf<String>()
    override fun defender(unSector: Sector, minion: Minion) {
        this.perderlaMitadDeLaEstamina(minion)
    }

    fun perderlaMitadDeLaEstamina(minion: Minion) {
        minion.estamina = (minion.estamina / 2)
    }
}

object Limpiador : Rol() {
    override fun defender(unSector: Sector, minion: Minion) = throw Exception("Me niego")
}
