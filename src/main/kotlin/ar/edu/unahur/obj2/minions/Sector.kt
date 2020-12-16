package ar.edu.unahur.obj2.minions

class Sector(var esGrande: Boolean, var estaLimpio: Boolean,var gradoDeAmenaza: Int) {

    fun sectorAtacado() {
        gradoDeAmenaza = 0
        estaLimpio = false
    }
    fun serLimpiado() {
        estaLimpio = true
    }
    fun tieneAmenaza()= gradoDeAmenaza == 0
}