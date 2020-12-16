package ar.edu.unahur.obj2.minions

object Laboratorio{

    var empleados = mutableListOf<Minion>()
    var sectores = mutableListOf<Sector>()
    var tareasPendientes = mutableListOf<Tarea>()

    fun enOrden() =
        sectores.all { it.estaLimpio }
                && sectores.all { it.tieneAmenaza() }
                && empleados.all { it.estaContento() }

    fun jonadaLaboral() {
        if (hayTareasPendientes()){ realizarTareasPendientes()}
        removerTareasRealizadas()
    }

    fun realizarTareasPendientes() {
        tareasPendientes.forEach { siAlgunoPuedeLoHace(it) }
    }

    fun siAlgunoPuedeLoHace(tarea: Tarea) {
        if (algunoCapaz(tarea)){
            realizarTarea(tarea)
            empleadoCapaz(tarea)?.agregarTarea(tarea)
            tareasPendientes.remove(tarea)
        }
        else {
            throw Exception("Ningun empleado puede hacer esta tarea")
        }
    }

    fun realizarTarea(tarea: Tarea) {
        this.empleadoCapaz(tarea)?.let { tarea.realizarLaTarea(it) }
    }

    fun hayTareasPendientes() = tareasPendientes.isNotEmpty()

    fun empleadoCapaz(tarea: Tarea) = empleados.find { tarea.puedeSerRealizada(it) }

    fun algunoCapaz(tarea: Tarea) = empleados.any { tarea.puedeSerRealizada(it) }

    fun removerTareasRealizadas(){ tareasPendientes.removeIf { !it.pendiente } }

}
