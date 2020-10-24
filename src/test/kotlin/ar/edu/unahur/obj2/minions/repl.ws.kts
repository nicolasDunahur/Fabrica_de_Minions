// Pueden usar este archivo para hacer pruebas rápidas,
// de la misma forma en que usaban el REPL de Wollok.

// OJO: lo que esté aquí no será tenido en cuenta
// en la corrección ni reemplaza a los tests.



abstract class Electro(val precio: Int, peso :Int){
  fun precio() = precio
  abstract fun espesaddo() : Boolean
 }

object Cocina : Electro(100,500){
    override fun espesaddo() = false

}

open class Lava(val secado : Boolean, precio: Int, peso:Int) : Electro(precio, peso){
    override fun espesaddo() = true

}

object LavaSeca : Lava(true,200,500){
    lateinit var wfi: String
}

val lava = Lava(false, 1000,500)

LavaSeca.precio
