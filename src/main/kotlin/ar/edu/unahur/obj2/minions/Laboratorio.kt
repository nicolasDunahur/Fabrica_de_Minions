package ar.edu.unahur.obj2.minions

import javax.management.relation.Role

object Laboratorio{
    lateinit var rolAsignado : Rol
    var empleados = mutableListOf<Minion>()


    fun enviarTarea(minion: Minion, tarea: Tarea) =
            if (tarea.puedeRealizarTarea(minion)) minion.tareaRealizadas.add(tarea)
            else throw Exception("no puede realizarla la tarea no cumple con los requisitos....")




}



class Sector(val esGrande: Boolean, val estaLimpio: Boolean,val gradoDeAmenaza: Int)
