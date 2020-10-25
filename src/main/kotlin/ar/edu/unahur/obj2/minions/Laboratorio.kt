package ar.edu.unahur.obj2.minions

import javax.management.openmbean.OpenMBeanInfo


object Laboratorio{

    var empleados = mutableListOf<Minion>()
    var sectores = mutableListOf<Sector>()
    var tareasPendientes = mutableListOf<Tarea>()

    fun enviarTarea(minion: Minion, tarea: Tarea) {
        if (tarea.puedeSerRealizada(minion)) {
            tarea.realizarLaTarea(minion)
            tareasPendientes.remove(tarea)
        }
        else throw Exception("no puede realizarla la tarea no cumple con los requisitos....")
    }

    fun asignarRol(minion: Minion,rolNuevo: Rol){ minion.rol = rolNuevo}

    fun enOrden() =
            sectores.all { it.estaLimpio }
                    && sectores.all { it.tieneAmenaza() }
                    && empleados.all { it.estaContento() }

    // recorre la lista de tareas con el empleado(si existe) que puede resolverlas
    fun jonadaLaboral() {
        if (hayTareasPendientes()) realizarTareasPendientes()
        else throw Exception("No hay tareas pendientes")
        removerTareasRealizadas()
    }

    fun realizarTareasPendientes() {
        tareasPendientes.forEach { siAlgunoPuedeLoHace(it) }
    }

    fun siAlgunoPuedeLoHace(tarea: Tarea) {
        if (algunoCapaz(tarea)){
            realizarTarea(tarea)
            empleadoCapaz(tarea)?.agregarTarea(tarea)
        }
        else {
            throw Exception("Ningun empleado puede hacer esta tarea")
        }
    }

    fun realizarTarea(tarea: Tarea) {
        this.empleadoCapaz(tarea)?.let { tarea.realizarLaTarea(it) }
        //tareasPendientes.remove(tarea)
    }

    fun hayTareasPendientes() = tareasPendientes.isNotEmpty()

    fun empleadoCapaz(tarea: Tarea) = empleados.find { tarea.puedeSerRealizada(it) }

    fun algunoCapaz(tarea: Tarea) = empleados.any { tarea.puedeSerRealizada(it) }

    fun removerTareasRealizadas(){ tareasPendientes.removeIf { !it.pendiente } }

}










