package ar.edu.unahur.obj2.minions

import javax.management.relation.Role

object Laboratorio {
    lateinit var rolAsignado : Role


}


class Sector(val esGrande: Boolean, val estaLimpio: Boolean,val gradoDeAmenaza: Int)

class Maquina(val complejidad: Int)