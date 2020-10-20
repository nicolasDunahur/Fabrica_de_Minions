package ar.edu.unahur.obj2.minions

interface Rol{}

object Soldado: Rol {
    var danio = 0

    fun usarArma(){
        danio += 2
    }

}
object Obrero : Rol{
    val herramientas = mutableListOf<String>()


}
object Limpiador: Rol  {

}

