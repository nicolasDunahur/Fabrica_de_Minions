package ar.edu.unahur.obj2.minions

import kotlin.math.min

abstract class Minion(val ojos: Int,var estamina:Int) {

    abstract fun puedeRealizarTarea() : Boolean
    open fun comerFruta(fruta:Fruta) {
        estamina += fruta.energia
    }
}

class Biclope(ojos: Int,estamina:Int) : Minion(ojos,estamina){

    override fun puedeRealizarTarea() = true
    override fun comerFruta(fruta: Fruta){
        estamina = min(10, fruta.energia + estamina)
    }
}

class Ciclople(ojos: Int,estamina: Int) : Minion(ojos,estamina) {

    override fun puedeRealizarTarea() = true
}



