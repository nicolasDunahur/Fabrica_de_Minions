import io.kotest.matchers.reflection.beAbstract
import kotlin.math.max
import kotlin.math.min

// Pueden usar este archivo para hacer pruebas rápidas,
// de la misma forma en que usaban el REPL de Wollok.

// OJO: lo que esté aquí no será tenido en cuenta
// en la corrección ni reemplaza a los tests.


 open class Ave(){
     var energia: Int = 0
}
object pepita :Ave(){
    fun comer(){
        energia = min(10, energia +2)
    }
}
pepita.energia
repeat(10){pepita.comer()}
pepita.energia


