package ar.edu.unahur.obj2.minions

object Laboratorio{
    lateinit var rolAsignado : Rol
    var empleados = mutableListOf<Minion>()


    fun enviarTarea(minion: Minion, tarea: Tarea) =
            if (tarea.tareaPuedeSerRealizada(minion)) minion.tareaRealizadas.add(tarea)
            else throw Exception("no puede realizarla la tarea no cumple con los requisitos....")

}




