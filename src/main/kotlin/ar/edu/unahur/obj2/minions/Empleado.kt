package ar.edu.unahur.obj2.minions

interface Empleado {
    var raza : Minion
    var rol : Rol
    var tarearRealizadas: MutableList<String>

    abstract fun puedeRealizarTarea() : Boolean
    open fun comerFruta(fruta:Fruta): Int

}