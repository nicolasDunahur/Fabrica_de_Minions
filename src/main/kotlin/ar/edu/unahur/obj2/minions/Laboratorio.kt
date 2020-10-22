package ar.edu.unahur.obj2.minions



class Laboratorio{


    var empleados = mutableListOf<Minion>()

    fun enviarTarea(minion: Minion, tarea: Tarea) {
        if (tarea.puedeSerRealizada(minion))   minion.tareaRealizadas.add(tarea)
        else throw Exception("no puede realizarla la tarea no cumple con los requisitos....")
    }
    fun asignarRol(minion: Minion,rolNuevo: Rol){ minion.rol = rolNuevo}


}









