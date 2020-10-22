package ar.edu.unahur.obj2.minions



class Laboratorio(){


    var empleados = mutableListOf<Minion>()
    var sectores = mutableListOf<Sector>()
    var tareasPendientes = mutableListOf<Tarea>()

    fun enviarTarea(minion: Minion, tarea: Tarea) {
        if (tarea.puedeSerRealizada(minion)) minion.agregarTarea(tarea)
        else throw Exception("no puede realizarla la tarea no cumple con los requisitos....")
    }
    fun asignarRol(minion: Minion,rolNuevo: Rol){ minion.rol = rolNuevo}

    fun enOrden() =
            sectores.all { it.estaLimpio }
                    && sectores.all { it.tieneAmenaza() }
                    && empleados.all { it.estaContento() }

    fun jonadaLaboral() {
        if (hayTareasPendientes()) {
            tareasPendientes.forEach {realizarTarea(it)}
        }
        else throw Exception("No hay tareas pendientes")
    }
    fun hayTareasPendientes() = tareasPendientes.isNotEmpty()

    fun empleadosCapaz(tarea: Tarea) = empleados.find { tarea.puedeSerRealizada(it) }
    // debe saltar en algun lado si no hay empleados capaces

    fun realizarTarea(tarea: Tarea) {
        empleadosCapaz(tarea)?.let { tarea.realizarLaTarea(it) }
        tareasPendientes.remove(tarea)
        empleadosCapaz(tarea)?.agregarTarea(tarea)

    }


}









