package ar.edu.unahur.obj2.minions

import kotlin.properties.Delegates

abstract class Minion(val ojos: Int,var estamina: Int) : Empleado{

    fun comerFruta(fruta:Fruta) { estamina += fruta.energia }

}
class Biclope(ojos: Int,estamina: Int) : Minion(ojos,estamina){

    override fun puedeRealizarTarea() = true

}

class Ciclople(ojos: Int,estamina: Int) : Minion(ojos,estamina){

    override fun puedeRealizarTarea() = true

}



