package ar.edu.unahur.obj2.minions



class Laboratorio(){


    var empleados = mutableListOf<Minion>()
    var sectores = mutableListOf<Sector>()
    var tareasPendientes = mutableListOf<Tarea>()

    fun enviarTarea(minion: Minion, tarea: Tarea) {
        if (tarea.puedeSerRealizada(minion)) minion.tareaRealizadas.add(tarea)
        else throw Exception("no puede realizarla la tarea no cumple con los requisitos....")
    }
    fun asignarRol(minion: Minion,rolNuevo: Rol){ minion.rol = rolNuevo}

    fun enOrden() =
            sectores.all { it.estaLimpio }
                    && sectores.all { it.tieneAmenaza() }
                    && empleados.all { it.estaContento() }
}









