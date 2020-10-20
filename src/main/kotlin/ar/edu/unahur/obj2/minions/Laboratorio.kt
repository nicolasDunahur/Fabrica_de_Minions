package ar.edu.unahur.obj2.minions

import javax.management.relation.Role

object Laboratorio {
    lateinit var rolAsignado : Role


}

abstract class Tarea(val minion: Minion, val sector: Sector, val dificultad: Int) {
    abstract fun requerimientos(minion: Minion)
    abstract fun efecto(minion: Minion)

}

class ArreglarMaquina() : Tarea()
class LimpiarSector() : Tarea()
class DefenderSector() : Tarea()



class Sector(val esGrande: Boolean, val estaLimpio: Boolean,val gradoDeAmenaza: Int)

class Maquina(val complejidad: Int)