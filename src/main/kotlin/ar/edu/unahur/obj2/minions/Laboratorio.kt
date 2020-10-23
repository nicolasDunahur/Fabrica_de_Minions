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

    // recorre la lista de tareas con el empleado(si existe) que puede resolverlas
    fun jonadaLaboral() {
        if (hayTareasPendientes()) tareasPendientes.map { siAlgunoPuedeLoHace(it) }
        else throw Exception("No hay tareas pendientes")
    }

    // si existe el que pueda resolver una tarea, la hace, se saca de pendientes y
    // la agrega a su registro.
    fun siAlgunoPuedeLoHace(tarea: Tarea) {
        if (algunoCapaz(tarea)){
            realizarTarea(tarea)
            tareasPendientes.remove(tarea)
            empleadoCapaz(tarea)?.agregarTarea(tarea)
        }
        else {
            throw Exception("Ningun empleado puede hacer esta tarea")
        }
    }

    // la tarea es realizada por el que puede
    fun realizarTarea(tarea: Tarea) = this.empleadoCapaz(tarea)?.let { tarea.realizarLaTarea(it) }

    fun hayTareasPendientes() = tareasPendientes.isNotEmpty()

    // trae al empleado capaz
    fun empleadoCapaz(tarea: Tarea) = empleados.find { tarea.puedeSerRealizada(it) }

    // existe alguien capaz?
    fun algunoCapaz(tarea: Tarea) = empleados.any { tarea.puedeSerRealizada(it) }







}









